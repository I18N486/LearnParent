#1.mapper生效的方式
    1.在启动类或者配置类上指定扫描
    @MapperScan("com.zst.mybatis.biz.dao")

    2.在mapper接口上添加注解，让框架自动扫描注入
    @Mapper

#2.分页功能的实现依赖分页插件（之前是pageHelper，现在可以直接使用框架内置的）
    1.别忘记配置分页插件
    @Configuration
    public class MybatisConfig {
        /**
         * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题
         */
        @Bean
        public MybatisPlusInterceptor mybatisPlusInterceptor() {
            MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
            interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
            return interceptor;
        }
    }

#3.mapper层选装件


#tips:
    1.#{}和${}的区别？
    ex：select * from user where name = #{name}; 
        动态解析：select * from user where name = ？; 
    ex：select * from user where name = '${name}'; 
        动态解析：select * from user where name = "dato"; 
    可以看到，#{}方式相当于prepareStatement，这相当于一个参数占位符；
            ${}方式相当于statement，这相当于一个字串替换。
    发生的过程：${}是在动态sql解析阶段，进行字符串替换操作；
                #{}是在DBMS过程中，进行参数填充。
    因此，${}方式容易存在sql注入漏洞，所以能用#{}的时候尽量用#{}，减少${}的使用.