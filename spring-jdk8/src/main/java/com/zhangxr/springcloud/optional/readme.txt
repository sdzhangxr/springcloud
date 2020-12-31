Optional 容器类
    代表一个值存在还是不存在，可以避免空指针

常用方法：
    1. Optional.of(T t); 创建一个Optional实例
    2. Optional.empty(); 创建一个空的Optional实例
    3. Optional.ofNullable(T t); 若t不为null,创建Optional实例
    4. isPresent(); 判断是否包含值
    5. orElse(T t); 如果调用对象包含值,返回该值.否则返回t.
    6. orElseGet(Supplier s); 如果调用对象包含值,返回该值.否则返回s获取的值.
    7. map(Function f); 如果有值对其处理，并返回处理后的Optional,否则返回Optional.empty();
    8. flatMap(Function mapper); 与map相似，要求返回值必须是Optional






