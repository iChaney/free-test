package com.qiang.bloom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 布隆过滤器
 *
 * @author liq
 * @date 2021/12/3 17:27
 */
public class BloomFilterTest {
    public static final String name_space = "sjz::";
    public static void main(String[] args) {
        BloomFilter<CharSequence> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charsets.UTF_8), 1000000, 0.03);
        for (int i = 0; i < 10; i++) {
            bloomFilter.put(name_space+"order::"+i);
        }
        System.out.println(bloomFilter.mightContain(name_space+"order::"+2));
    }
}
