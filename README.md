IKAnanlyzer4Pinyin
==================

Implemented to transform chinese to pinyin after the sentence
participle to words by IKAnaylzer.
实现了在使用IKAnaylzer分词后，把单词转换成拼音来作为索引

修改了IKAnanlyzer的部分代码，使IKAnanlyzer支持了分词后再把词转换成拼音或者首字母来做索引，IKAznzylzer的作者博客，
http://linliangyi2007.iteye.com/，先感谢他的贡献。

汉字转拼音用到了github上的 jpinyin 开源库，感谢作者，https://github.com/stuxuhai/jpinyin。

现在只需要IKAnanlyzer4Pinyin，你就可以实现中文分词，分词后全拼和首字母的索引。

主要用在搜索建议，通讯录搜索等需要首字母和全拼搜索的地方。

性能，在我数据测试中，全拼和首字母都建立索引，建索引时间增加了20%。

其中主要使用到了PerFieldAnalyzerWrapper，它可以实现对不同field的使用不用的anaylzer。

demo在sample的LuceneIndexAndSearchDemo中

我的邮箱rebricate@gmail.com
