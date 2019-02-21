package com.jiang;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.ImmutableSet;
import com.jiang.entity.Ele;

import java.util.*;

/**
 * @description:
 * @author:
 * @create: 2019-02-21 19:36
 */
public class GuavaTest {

    /**
     * 按照ele的id对元素进行归类，
     * 往Map中 key存的是 id 每个Key下面挂一个 FluentIterable 的链表，
     * 之后在遍历 key下挂的每个链表的时候 进行 prediect 比较，并输出结果
     * @param args
     */
    public static void main(String[] args) {
        List<Ele> list = Arrays.asList(new Ele[]{
                new Ele(1,7),// 7/3/2/4/9/5
                new Ele(1,3),
                new Ele(1,2),
                new Ele(1,3),
                new Ele(1,4),
                new Ele(1,9),
                new Ele(1,5),
                new Ele(2,8),// 8/7/6/3/2
                new Ele(2,6),
                new Ele(2,7),
                new Ele(2,7),
                new Ele(2,3),
                new Ele(2,2),
                new Ele(2,2),
                new Ele(2,2)});
        Map<String ,Object> result = new HashMap<>();
        for(Ele ele : list){
            Predicate<Ele> alwaysTrue = Predicates.alwaysTrue();
            ElePredicate elePredicate = new ElePredicate(ele.getId());
            Predicate<Ele> and = Predicates.and(alwaysTrue, elePredicate);
//            Predicate<Ele> and = Predicates.and( new Predicate<Ele>() {
//                @Override
//                public boolean apply(Ele input) {
//                    return input.getI() > 5;
//                }
//            },alwaysTrue);
            //ImmutableSet<Ele> eles = FluentIterable.from(list).filter(and).toSet();// 过滤出符合条件（and对象返回值为true的结果集），并去重
            FluentIterable<Ele> eles = FluentIterable.from(list).filter(and);// 过滤出符合条件（and对象返回值为true的结果集），不去重
            result.put(String.valueOf(ele.getId()), eles);
        }

        System.out.println(JSON.toJSONString(result));// FluentIterable只输出 empty true/false  ImmutableSet输出 全部内容

        for(String key : result.keySet()){
            System.out.println(String.format("-------%s------", key));
            Iterable<Ele> iter = (Iterable<Ele>)result.get(key);
            Iterator<Ele> iterator = iter.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().getI());
            }
        }
    }



}

class ElePredicate implements Predicate<Ele>{

    Integer id;

    ElePredicate(Integer id){
        this.id = id;
    }

    @Override
    public boolean apply(Ele ele) {
        return this.id.intValue() == ele.getId();
    }
}

