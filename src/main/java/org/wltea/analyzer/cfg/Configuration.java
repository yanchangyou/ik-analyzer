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
 */
package org.wltea.analyzer.cfg;

import org.wltea.analyzer.dic.DictSegment;

/**
 * 
 * 配置管理类接口
 * 修改：接口中使用字典，不使用路径，支持更大的自由度 2015-12-7 yanchangyou
 * 
 */
public interface Configuration {
	
	/**
	 * 返回useSmart标志位
	 * useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * @return useSmart
	 */
	public boolean useSmart();
	
	/**
	 * 设置useSmart标志位
	 * useSmart =true ，分词器使用智能切分策略， =false则使用细粒度切分
	 * @param useSmart
	 */
	public void setUseSmart(boolean useSmart);
		
	/**
     * 主词典对象
     */
    public DictSegment getMainDict();
    
    /**
     * 停止词词典 
     */
    public DictSegment getStopWordDict();
    
    /**
     * 量词词典
     */
    public DictSegment getQuantifierDict();
    
}
