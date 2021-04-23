package com.zst.mybatis;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zst.mybatis.api.pojo.entity.User;
import com.zst.mybatis.biz.dao.UserMapper;
import com.zst.mybatis.biz.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/4/22 16:53
 **/

@SpringBootTest
@RunWith(SpringRunner.class)
public class BaseCRUDDemo {

    @Autowired
    IUserService userService;
    @Autowired
    UserMapper userMapper;

    /**
     * IService封装的基础crud方法
     */
    @Test
    public void testCrudByService(){
        /**
         * 保存方法：
         *  // 插入一条记录（选择字段，策略插入）
         *  boolean save(T entity);
         *  // 插入（批量）
         *  boolean saveBatch(Collection<T> entityList);
         *  // 插入（批量）,batchSize 一次插入的數量
         *  boolean saveBatch(Collection<T> entityList, int batchSize);
         *  // TableId 注解存在更新记录，否插入一条记录
         *  boolean saveOrUpdate(T entity);
         *  // 根据updateWrapper尝试更新，否继续执行saveOrUpdate(T)方法
         *  boolean saveOrUpdate(T entity, Wrapper<T> updateWrapper);
         *  // 批量修改插入
         *  boolean saveOrUpdateBatch(Collection<T> entityList);
         *  // 批量修改插入
         *  boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);
         */
        /*
        User user = new User().setName("哆俐").setAge(4).setEmail("duoli@mp.com");
        User user1 = new User().setName("多隆").setAge(108).setEmail("duolong@mp.com");
        List<User> userList = new LinkedList<>();
        userList.add(user);
        userList.add(user1);
        userService.saveBatch(userList,2);
        */


        /**
         * 删除方法：
         * // 根据 entity 条件，删除记录
         * boolean remove(Wrapper<T> queryWrapper);
         * // 根据 ID 删除
         * boolean removeById(Serializable id);
         * // 根据 columnMap 条件，删除记录（值等于作为条件）
         * boolean removeByMap(Map<String, Object> columnMap);
         * // 删除（根据ID 批量删除）
         * boolean removeByIds(Collection<? extends Serializable> idList);
         */
        /*
        HashMap<String,Object> conditionMap = new HashMap<>();
        conditionMap.put("name","多隆");
        conditionMap.put("age",108);
        userService.removeByMap(conditionMap);
         */

        /**
         * 更新方法
         * // 根据 UpdateWrapper 条件，更新记录 需要设置sqlset
         * boolean update(Wrapper<T> updateWrapper);
         * // 根据 whereEntity 条件，更新记录
         * boolean update(T entity, Wrapper<T> updateWrapper);
         * // 根据 ID 选择修改
         * boolean updateById(T entity);
         * // 根据ID 批量更新
         * boolean updateBatchById(Collection<T> entityList);
         * // 根据ID 批量更新
         * boolean updateBatchById(Collection<T> entityList, int batchSize);
         */
        /*
        //set方法对属性设置，设置将要更新的内容，配合添加的条件进行条件更新（eq、ne、ge、le、like）
        UpdateWrapper<User> updateWrapper = Wrappers.<User>update().set("name","小羊哆莉")
                .eq("name","哆俐");
        User user = new User().setAge(4).setName("小羊哆莉");
        //注意区分，这里setEntity实际上是设置条件，全是值等于的条件
        UpdateWrapper<User> updateWrapper1 = Wrappers.<User>update().set("age",8)
                .setEntity(user);
        userService.update(updateWrapper1);
        //注意这里区分实体就是要更新的值，由updateWrapper作为条件查询匹配
        LambdaUpdateWrapper<User> lambdaUpdateWrapper = Wrappers.<User>lambdaUpdate()
                .eq(User::getName,"小羊哆莉");
        User updateV = new User().setAge(10).setName("大羊哆莉");
        userService.update(updateV,lambdaUpdateWrapper);
        */


        /**
         * 查询方法：
         * // 根据 ID 查询
         * T getById(Serializable id);
         * // 根据 Wrapper，查询一条记录。结果集，如果是多个会抛出异常，随机取一条加上限制条件 wrapper.last("LIMIT 1")
         * T getOne(Wrapper<T> queryWrapper);
         * // 根据 Wrapper，查询一条记录,false 当匹配多条时不抛异常；
         * T getOne(Wrapper<T> queryWrapper, boolean throwEx);
         * // 根据 Wrapper，查询一条记录
         * Map<String, Object> getMap(Wrapper<T> queryWrapper);
         * // 根据 Wrapper，查询一条记录 todo 该方法不清楚具体使用
         * <V> V getObj(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
         * // 查询所有
         * List<T> list();
         * // 查询列表
         * List<T> list(Wrapper<T> queryWrapper);
         * // 查询（根据ID 批量查询）
         * Collection<T> listByIds(Collection<? extends Serializable> idList);
         * // 查询（根据 columnMap 条件）
         * Collection<T> listByMap(Map<String, Object> columnMap);
         * // 查询所有列表
         * List<Map<String, Object>> listMaps();
         * // 查询列表
         * List<Map<String, Object>> listMaps(Wrapper<T> queryWrapper);
         * // 查询全部记录
         * List<Object> listObjs();
         * // 查询全部记录
         * <V> List<V> listObjs(Function<? super Object, V> mapper);
         * // 根据 Wrapper 条件，查询全部记录
         * List<Object> listObjs(Wrapper<T> queryWrapper);
         * // 根据 Wrapper 条件，查询全部记录
         * <V> List<V> listObjs(Wrapper<T> queryWrapper, Function<? super Object, V> mapper);
         */
        LambdaQueryWrapper<User> lambdaQueryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getName,"大羊哆莉");
        User one = userService.getOne(lambdaQueryWrapper, false);
        System.out.println(one);
        List<User> userList = userService.list(lambdaQueryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }


        /**
         * 分页查询(注意需要配置分页插件（MybatisConfig里统一配置）才会生效)
         * // 无条件分页查询
         * IPage<T> page(IPage<T> page);
         * // 条件分页查询
         * IPage<T> page(IPage<T> page, Wrapper<T> queryWrapper);
         * // 无条件分页查询
         * IPage<Map<String, Object>> pageMaps(IPage<T> page);
         * // 条件分页查询
         * IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper);
         *
         * 计数：
         * // 查询总记录数
         * int count();
         * // 根据 Wrapper 条件，查询总记录数
         * int count(Wrapper<T> queryWrapper);
         */
        Page<User> userPage = userService.page(new Page<>(5, 3));
        System.out.println("total:"+userPage.getTotal()+",current:"+userPage.getCurrent());
        for (User record : userPage.getRecords()) {
            System.out.println(record);
        }


        /**
         * // 链式查询 普通
         * QueryChainWrapper<T> query();
         * // 链式查询 lambda 式。注意：不支持 Kotlin
         * LambdaQueryChainWrapper<T> lambdaQuery();
         *
         * // 示例：
         * query().eq("column", value).one();
         * lambdaQuery().eq(Entity::getId, value).list();
         *
         * // 链式更改 普通
         * UpdateChainWrapper<T> update();
         * // 链式更改 lambda 式。注意：不支持 Kotlin
         * LambdaUpdateChainWrapper<T> lambdaUpdate();
         *
         * // 示例：
         * update().eq("column", value).remove();
         * lambdaUpdate().eq(Entity::getId, value).update(entity);
         */
        //注意链式的查询one方法，当匹配多个结果时会抛异常
        //User jack = userService.query().eq("name", "Jack").one();
        //System.out.println(jack);
        List<User> list = userService.lambdaQuery().eq(User::getName, "Jack").list();
        for (User user : list) {
            System.out.println(user);
        }
    }


    /**
     * BaseMapper封装的CRUD方法
     */
    @Test
    public void testCrudByMapper(){

        /**
         * 插入数据
         * // 插入一条记录
         * int insert(T entity);
         */
        User user = new User().setName("王五").setAge(28);
        userMapper.insert(user);

        /**
         * 删除数据
         * // 根据 entity 条件，删除记录
         * int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
         * // 删除（根据ID 批量删除）
         * int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
         * // 根据 ID 删除
         * int deleteById(Serializable id);
         * // 根据 columnMap 条件，删除记录
         * int deleteByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
         */
        userMapper.deleteById(3);

        /**
         * 更新数据
         * // 根据 whereEntity 条件，更新记录
         * int update(@Param(Constants.ENTITY) T entity, @Param(Constants.WRAPPER) Wrapper<T> updateWrapper);
         * // 根据 ID 修改
         * int updateById(@Param(Constants.ENTITY) T entity);
         */
        User wangwu = user.setEmail("wangwu@mq.com");
        userMapper.updateById(wangwu);

        /**
         * 查询数据
         * // 根据 ID 查询
         * T selectById(Serializable id);
         * // 根据 entity 条件，查询一条记录
         * T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         *
         * // 查询（根据ID 批量查询）
         * List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
         * // 根据 entity 条件，查询全部记录
         * List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         * // 查询（根据 columnMap 条件）
         * List<T> selectByMap(@Param(Constants.COLUMN_MAP) Map<String, Object> columnMap);
         * // 根据 Wrapper 条件，查询全部记录
         * List<Map<String, Object>> selectMaps(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         * // 根据 Wrapper 条件，查询全部记录。注意： 只返回第一个字段的值
         * List<Object> selectObjs(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         *
         * // 根据 entity 条件，查询全部记录（并翻页） 分页都依赖分页插件
         * IPage<T> selectPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         * // 根据 Wrapper 条件，查询全部记录（并翻页）
         * IPage<Map<String, Object>> selectMapsPage(IPage<T> page, @Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         * // 根据 Wrapper 条件，查询总记录数
         * Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
         */
    }


    /**
     * Wrapper具备的方法的功能
     * 父类：AbstractWrapper
     * 子类：QueryWrapper、LambdaQueryWrapper、UpdateWrapper、LambdaUpdateWrapper
     * QueryWrapper(LambdaQueryWrapper) 和 UpdateWrapper(LambdaUpdateWrapper) 的父类
     * 用于生成 sql 的 where 条件, entity 属性也用于生成 sql 的 where 条件
     * 注意: entity 生成的 where 条件与 使用各个 api 生成的 where 条件没有任何关联行为
     *
     * 父类基本方法，对应sql的条件命令词：
     * allEq(Map<R, V> params)
     * allEq(Map<R, V> params, boolean null2IsNull)
     * allEq(boolean condition, Map<R, V> params, boolean null2IsNull)
     * 个别参数说明:
     * params : key为数据库字段名,value为字段值
     * null2IsNull : 为true则在map的value为null时调用 isNull 方法,为false时则忽略value为null的
     * ex：allEq({id:1,name:"老王",age:null}, false)--->id = 1 and name = '老王'
     *
     * allEq(BiPredicate<R, V> filter, Map<R, V> params)
     * allEq(BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
     * allEq(boolean condition, BiPredicate<R, V> filter, Map<R, V> params, boolean null2IsNull)
     * 个别参数说明:
     * filter : 过滤函数,是否允许字段传入比对条件中
     * ex: allEq((k,v) -> k.indexOf("a") >= 0, {id:1,name:"老王",age:null})--->name = '老王' and age is null
     *
     * eq(R column, Object val)
     * eq(boolean condition, R column, Object val)
     * ex: eq("name", "老王")--->name = '老王'
     *
     * ne(R column, Object val)
     * ne(boolean condition, R column, Object val)
     * ex: ne("name", "老王")--->name <> '老王'
     *
     * gt(R column, Object val)
     * gt(boolean condition, R column, Object val)
     * ex: gt("age", 18)--->age > 18
     *
     * ge(R column, Object val)
     * ge(boolean condition, R column, Object val)
     * ex: ge("age", 18)--->age >= 18
     *
     * lt(R column, Object val)
     * lt(boolean condition, R column, Object val)
     * ex: lt("age", 18)--->age < 18
     *
     * le(R column, Object val)
     * le(boolean condition, R column, Object val)
     * ex: le("age", 18)--->age <= 18
     *
     * between(R column, Object val1, Object val2)
     * between(boolean condition, R column, Object val1, Object val2)
     * ex: between("age", 18, 30)--->age between 18 and 30
     *
     * notBetween(R column, Object val1, Object val2)
     * notBetween(boolean condition, R column, Object val1, Object val2)
     * ex: notBetween("age", 18, 30)--->age not between 18 and 30
     *
     * like(R column, Object val)
     * like(boolean condition, R column, Object val)
     * ex: like("name", "王")--->name like '%王%'
     *
     * notLike(R column, Object val)
     * notLike(boolean condition, R column, Object val)
     * ex: notLike("name", "王")--->name not like '%王%'
     *
     * likeLeft(R column, Object val)
     * likeLeft(boolean condition, R column, Object val)
     * ex: likeLeft("name", "王")--->name like '%王'
     *
     * likeRight(R column, Object val)
     * likeRight(boolean condition, R column, Object val)
     * ex: likeRight("name", "王")--->name like '王%'
     *
     * isNull(R column)
     * isNull(boolean condition, R column)
     * ex: isNull("name")--->name is null
     *
     * isNotNull(R column)
     * isNotNull(boolean condition, R column)
     * ex: isNotNull("name")--->name is not null
     *
     * in(R column, Collection<?> value)
     * in(boolean condition, R column, Collection<?> value)
     * in(R column, Object... values)
     * in(boolean condition, R column, Object... values)
     * ex: in("age",{1,2,3})--->age in (1,2,3)
     *
     * notIn(R column, Collection<?> value)
     * notIn(boolean condition, R column, Collection<?> value)
     * notIn(R column, Object... values)
     * notIn(boolean condition, R column, Object... values)
     * ex: notIn("age", 1, 2, 3)--->age not in (1,2,3)
     *
     * inSql(R column, String inValue)
     * inSql(boolean condition, R column, String inValue)
     * notInSql(R column, String inValue)
     * notInSql(boolean condition, R column, String inValue)
     * ex: inSql("age", "1,2,3,4,5,6")--->age in (1,2,3,4,5,6)
     * ex: inSql("id", "select id from table where id < 3")--->id in (select id from table where id < 3)
     *
     * groupBy(R... columns)
     * groupBy(boolean condition, R... columns)
     * ex: groupBy("id", "name")--->group by id,name
     *
     * orderByAsc(R... columns)
     * orderByAsc(boolean condition, R... columns)
     * orderByDesc(R... columns)
     * orderByDesc(boolean condition, R... columns)
     * orderBy(boolean condition, boolean isAsc, R... columns)
     * ex: orderByAsc("id", "name")--->order by id ASC,name ASC
     *
     * having(String sqlHaving, Object... params)
     * having(boolean condition, String sqlHaving, Object... params)
     * ex: having("sum(age) > {0}", 11)--->having sum(age) > 11
     *
     * func(Consumer<Children> consumer)
     * func(boolean condition, Consumer<Children> consumer)
     * ex: func(i -> if(true) {i.eq("id", 1)} else {i.ne("id", 1)})
     *
     * or()
     * or(boolean condition)
     * or(Consumer<Param> consumer)
     * or(boolean condition, Consumer<Param> consumer)
     * ex: or(i -> i.eq("name", "李白").ne("status", "活着"))--->or (name = '李白' and status <> '活着')
     *
     * and(Consumer<Param> consumer)
     * and(boolean condition, Consumer<Param> consumer)
     * ex: and(i -> i.eq("name", "李白").ne("status", "活着"))--->and (name = '李白' and status <> '活着')
     *
     * nested(Consumer<Param> consumer)
     * nested(boolean condition, Consumer<Param> consumer)
     * ex: nested(i -> i.eq("name", "李白").ne("status", "活着"))--->(name = '李白' and status <> '活着')
     *
     * apply(String applySql, Object... params)
     * apply(boolean condition, String applySql, Object... params)
     * ex: apply("date_format(dateColumn,'%Y-%m-%d') = {0}", "2008-08-08")--->date_format(dateColumn,'%Y-%m-%d') = '2008-08-08'")
     *
     * last(String lastSql)
     * last(boolean condition, String lastSql)
     * 无视优化规则直接拼接到sql最后，只能调用一次，存在 sql注入风险
     * ex: last("limit 1")
     *
     * exists(String existsSql)
     * exists(boolean condition, String existsSql)
     * notExists(String notExistsSql)
     * notExists(boolean condition, String notExistsSql)
     * ex：exists("select id from table where age = 1")--->exists (select id from table where age = 1)
     *
     * select 用于取特定的字段，不使用则相当于select *；
     * todo select 的predicate用法不理解，如下例，报空指针异常
     *
     * set 用于给字段设值
     * setSql 用于添加sql（赋值的sql）
     */
    @Test
    public void testWrapper(){
        QueryWrapper<User> queryWrapper = Wrappers.<User>query().select("name","age")
                .eq("id",5);

//        QueryWrapper<User> queryWrapper1 = Wrappers.<User>query().select(i-> {
//            if (i != null) {
//                System.out.println(i.getColumn() + "," + i.getProperty() + "," + i.getField() + "," + i.getPropertyType());
//            }
//            return true;
//        })
//                .eq("id",5);
        User user = userService.getOne(queryWrapper, false);
        System.out.println(user);

        UpdateWrapper<User> updateWrapper = Wrappers.<User>update().setSql("name = 'doli'")
                .eq("name","大羊哆莉");
        userService.update(updateWrapper);
    }

    /**
     * 保留支持注解的方式自定义sql
     * 升级，支持使用wrapper，注意获取wrapper的方式：
     * 1.添加参数名注解@Param(Constants.WRAPPER)
     * 2.sql中取值固定采用 ${ew.customSqlSegment}
     * tips：注意wrapper自带了where命令词，自定义sql中无需再加
     *
     * 保留在xml中编写sql
     * 升级，支持使用wrapper
     */
    @Test
    public void testAnnotation(){
        String name = userMapper.getNameById(6l);
        System.out.println(name);

        LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery()
                .eq(User::getAge,10)
                .likeRight(User::getName,"do");
        List<User> userList = userMapper.getByWrapper(queryWrapper);
        for (User user : userList) {
            System.out.println(user);
        }
    }
}
