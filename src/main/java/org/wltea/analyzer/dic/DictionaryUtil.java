/**
 * IK 中文分词  版本 5.0
 * IK Analyzer release 5.0
 * 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * 源代码由林良益(linliangyi2005@gmail.com)提供
 * 版权声明 2012，乌龙茶工作室
 * provided by Linliangyi and copyright 2012 by Oolong studio
 * 
 * 
 */
package org.wltea.analyzer.dic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;

/**
 * 词典工具类
 */
public class DictionaryUtil {

    /**
     * 批量加载新词条
     * 
     * @param words Collection<String>词条列表
     */
    public static void addWords(DictSegment dic,
                                Collection<String> words) {
        if (words != null) {
            for (String word : words) {
                if (word != null) {
                    // 批量加载词条到主内存词典中
                    dic.fillSegment(word.trim().toLowerCase().toCharArray());
                }
            }
        }
    }

    /**
     * 批量移除（屏蔽）词条
     * 
     * @param words
     */
    public void disableWords(DictSegment dic,
                             Collection<String> words) {
        if (words != null) {
            for (String word : words) {
                if (word != null) {
                    // 批量屏蔽词条
                    dic.disableSegment(word.trim().toLowerCase().toCharArray());
                }
            }
        }
    }

    /**
     * 从已匹配的Hit中直接取出DictSegment，继续向下匹配
     * 
     * @param charArray
     * @param currentIndex
     * @param matchedHit
     * @return Hit
     */
    public static Hit matchWithHit(char[] charArray,
                                   int currentIndex,
                                   Hit matchedHit) {
        DictSegment ds = matchedHit.getMatchedDictSegment();
        return ds.match(charArray, currentIndex, 1, matchedHit);
    }

    /**
     * 加载主词典及扩展词典
     */
    public static void loadDict(DictSegment dic,
                                String dir) {
        // 读取主词典文件
        InputStream is = dic.getClass().getClassLoader().getResourceAsStream(dir);
        if (is == null) {
            throw new RuntimeException("Main Dictionary not found!!!");
        }

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"), 512);
            String theWord = null;
            do {
                theWord = br.readLine();
                if (theWord != null && !"".equals(theWord.trim())) {
                    dic.fillSegment(theWord.trim().toLowerCase().toCharArray());
                }
            } while (theWord != null);

        } catch (IOException ioe) {
            System.err.println("Main Dictionary loading exception.");
            ioe.printStackTrace();

        } finally {
            try {
                if (is != null) {
                    is.close();
                    is = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 
     * 功能描述: 批量添加
     * 
     * @param dic
     * @param dirs void
     * @version 2.0.0
     * @author yanchangyou(15070440)
     */
    public static void loadDict(DictSegment dic,
                                String[] dirs) {
        for (String dir : dirs) {
            loadDict(dic, dir);
        }
    }
}
