## 基于Redisson实现分布式锁

1. 利用api，手动加锁、释放锁
2. 利用aop，自定义注解，自动加锁、释放锁
   1. 基于aop，会出现锁失效的情况，原因为aop失效导致锁失效。