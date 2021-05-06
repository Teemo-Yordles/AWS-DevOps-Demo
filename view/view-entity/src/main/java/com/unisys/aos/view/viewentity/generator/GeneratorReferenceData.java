package com.unisys.aos.view.viewentity.generator;

import java.io.InputStream;

/**
 * 用于生产MBG的代码
 * Created by Liu Jie 2020/09/04.
 */
public class GeneratorReferenceData {
    public static void main(String[] args) throws Exception {
        //读取我们的 MBG 配置文件
        InputStream is = GeneratorReferenceData.class.getResourceAsStream("/generatorConfig_reference_data.xml");
        GeneratorUtil.generate(is);
    }
}
