/**
 * $Id$
 * Copyright 2009-2012 Oak Pacific Interactive. All rights reserved.
 */
package org.wltea4pinyin.analyzer.pinyin;

import opensource.jpinyin.PinyinFormat;
import opensource.jpinyin.PinyinHelper;


/**
 * 
 * 
 * @author <a href="mailto:liu.gang@renren-inc.com">刘刚</a>
 * @version 2013-7-9上午11:40:14
 */
public class ConverToPinyin implements PinyinChoose{
     public String convert(String input){
         return PinyinHelper.convertToPinyinString(input, "", PinyinFormat.WITHOUT_TONE);
     }
}

