package com.forte.util.mockbean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ForteScarlet <[163邮箱地址]ForteScarlet@163.com>
 * @date Created in 2019/2/14 16:52
  @since JDK1.8
 **/
public class MockMapBean extends MockBean<Map> {


    /**
     * 重写MockBean的方法，返回Map封装对象
     * @return
     */
    @Override
    public Map<String, Object> getObject(){
        //假字段集
        MockField[] fields = this.getFields();

        //使用多线程执行
        return Arrays.stream(fields).parallel().flatMap(f -> {
            //保存字段映射
            HashMap<String, Object> map = new HashMap<>(1);
            map.put(f.getFieldName(), f.getValue());
            return map.entrySet().stream();
        }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));



    }


    /**
     * 构造方法
     *
     * @param fields
     */
    public MockMapBean(MockField[] fields) {
        super(Map.class, fields);
    }
}
