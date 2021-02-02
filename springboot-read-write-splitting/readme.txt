1.Maven依赖
    <properties>
        <java.version>1.8</java.version>
    </properties>

    <dependencies>
        <!--web模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
        <!--（健康监控）配置和使用-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        <!--aop切面模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-aop</artifactId>
        </dependency>
        <!-- JDBC模块-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-jdbc</artifactId>
        </dependency>
        <!-- mybatis集成spring-->
        <dependency>
            <groupId>org.mybatis.spring.boot</groupId>
            <artifactId>mybatis-spring-boot-starter</artifactId>
        </dependency>
        <!-- commons-lang3 -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-lang3</artifactId>
            <version>3.9</version>
        </dependency>
        <!-- mysql连接jar -->
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
        </dependency>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-test</artifactId>
            <scope>test</scope>
        </dependency>
        <!-- fastjson -->
        <dependency>
            <groupId>com.alibaba</groupId>
            <artifactId>fastjson</artifactId>
            <version>1.2.58</version>
        </dependency>
        <!-- spring-boot-devtools -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-devtools</artifactId>
            <optional>true</optional>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

2.配置文件application.yml
spring:
  datasource:
    master:
      jdbc-url: jdbc:mysql://localhost:3306/testmaster?serverTimezone=UTC
      username: root
      password: 111111
      driver-class-name: com.mysql.jdbc.Driver
    slave:
      jdbc-url: jdbc:mysql://localhost:3306/testslave?serverTimezone=UTC
      username: root
      password: 111111
      driver-class-name: com.mysql.jdbc.Driver

3.配置3个数据源，一个主数据源，一个从数据源，一个路由数据源
前两个数据源都是为了生成第三个数据源，后续我们只用最后一个路由数据源
@Configuration
public class DataSourceConfig {

    @Bean("masterDataSource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean("slaveDataSource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    @Primary
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource){
        Map<Object,Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DBTypeEnum.MASTER,masterDataSource);
        targetDataSource.put(DBTypeEnum.SLAVE,slaveDataSource);
        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSource);
        return myRoutingDataSource;
    }
}

4.现在有三个数据源，我们要为事务管理器和mybatis手动指定一个明确的数据源
@Configuration
public class MybatisConfig {

    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setMapperLocations(
                new PathMatchingResourcePatternResolver()
                        .getResources("classpath:mapper/*.xml")
        );
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager platformTransactionManager(){
        return new DataSourceTransactionManager(myRoutingDataSource);
    }
}

5.设置路由key/查找数据源
定义枚举类代表三个数据源
public enum  DBTypeEnum {
    MASTER,SLAVE
}

6.通过ThreadLocal将数据源设置到每个线程的上下文
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger(-1);

    public static void set(DBTypeEnum dbType){
        contextHolder.set(dbType);
    }

    public static DBTypeEnum get(){
        return contextHolder.get();
    }

    public static void master(){
        set(DBTypeEnum.MASTER);
        System.out.println("------切换到master数据源------");
    }

    public static void slave(){
        set(DBTypeEnum.SLAVE);
        System.out.println("------切换到slave数据源------");
    }
}

7.路由获取
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}

8.默认情况下，所有查询走从库，增删改走主库。
我们通过方法名来区分操作类型，用Aop配置来实现
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.zhangxr.springcloud.annotation.Master) " +
            "&& (execution(* com.zhangxr.springcloud.service..*.select*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.get*(..))) ")
    public void readPointcut(){}

    @Pointcut("@annotation(com.zhangxr.springcloud.annotation.Master) " +
            "|| execution(* com.zhangxr.springcloud.service..*.insert*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.add*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.update*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.edit*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.delete*(..)) " +
            "|| execution(* com.zhangxr.springcloud.service..*.remove*(..)) ")
    public void writePointcut(){}

    @Before("readPointcut()")
    public void read(){
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write(){
        DBContextHolder.master();
    }
}

9.如果特殊情况下，强制查主库。通过自定义注解来实现
public @interface Master {
}











