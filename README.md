### adapter-bank-spring-boot-starter

**Adapter-bank-spring-boot-starter** - это подключаемый Spring Boot модуль для стандартных 
компонентов proxy-алаптеров. 

Для корректной работы Вашего адаптера необходимо:
*  подключить необходимые properties (AdapterProperties, TimerProperties). Если поля родителя
   не покрывают всех необходимых бизнес-полей, то необходимо унаследоваться от родителя и 
   при помощи аннотаций @Configuration и @ConfigurationProperties создать соответствующий
   класс конфигураций .Если поля родителя полностью покрывают case, то можно не создавать 
   класс-наследник, а создать соответствующий bean в конфигурации. Например:


    @Bean
    @Primary
    @ConfigurationProperties("time.config")
    public TimerProperties timerProperties() {
        return new TimerProperties();
    }
    
    