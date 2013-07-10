/**
 * IK 中文分词  版本 5.0.1
 * IK Analyzer release 5.0.1
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
package org.wltea4pinyin.analyzer.lucene;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.wltea4pinyin.analyzer.pinyin.ConverToHanzi;
import org.wltea4pinyin.analyzer.pinyin.ConverToPinyin;
import org.wltea4pinyin.analyzer.pinyin.ConverToShouzimu;
import org.wltea4pinyin.analyzer.pinyin.PinyinChoose;

/**
 * IK分词器，Lucene Analyzer接口实现
 * 兼容Lucene 4.0版本 由R(rebricate@gmail.com)添加了对汉字转全拼和首字母的支持
 * 汉字拼音的转换采用的是https://github.com/stuxuhai/jpinyin 这个工程，
 * 使用方法看sample里面的LuceneIndexAndSearchDemo
 */
public final class IKAnalyzer4PinYin extends Analyzer{


    public static final int HANZI = 0;
    
    public static final int PINYIN = 1;

    public static final int PINYIN_SHOUZIMU = 2;
    
	private boolean useSmart;
	
	private PinyinChoose pinyinChoose;
	
	public boolean useSmart() {
		return useSmart;
	}

	public void setUseSmart(boolean useSmart) {
		this.useSmart = useSmart;
	}

	public PinyinChoose usePinyinChoose() {
        return pinyinChoose;
    }

    public void setPinyinChoose(PinyinChoose pinyinChoose) {
        this.pinyinChoose = pinyinChoose;
    }

	
	
	/**
	 * IK分词器Lucene  Analyzer接口实现类
	 * 
	 * 默认细粒度切分算法
	 */
	public IKAnalyzer4PinYin(){
		this(false, HANZI);
	}
	
	/**
     * IK分词器Lucene  Analyzer接口实现类
     * 
     * 默认细粒度切分算法，和汉子分词
     */
    public IKAnalyzer4PinYin(boolean useSmart){
        this(useSmart, HANZI);
    }
    
	/**
	 * IK分词器Lucene Analyzer接口实现类
	 * 
	 * @param useSmart 当为true时，分词器进行智能切分，可以选择是做全拼索引还是，首字母索引
	 */
	public IKAnalyzer4PinYin(boolean useSmart, int pinyinChoose){
		super();
		this.useSmart = useSmart;
		this.pinyinChoose = getPinyinChoose(pinyinChoose);
		//此段代码是为了支持拼音而加的
	}

	/**
	 * 重载Analyzer接口，构造分词组件
	 */
	@Override
	protected TokenStreamComponents createComponents(String fieldName, final Reader in) {
		Tokenizer _IKTokenizer = new IKTokenizer(in , this.useSmart(), this.usePinyinChoose());
		return new TokenStreamComponents(_IKTokenizer);
	}

	private PinyinChoose getPinyinChoose(int type){
	    if(type == PINYIN){
            return (new ConverToPinyin());
        }
        else if(type == PINYIN_SHOUZIMU){
            return (new ConverToShouzimu());
        }
        else {
            return (new ConverToHanzi());
        }
	}
}
