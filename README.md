#Android 下划线的不同画法

Android 原生的下划线是沿着文字基线(Baseline)绘制。中文显示浏览起来效果不好，所以试试用两种方式将下划线绘制在下降线 ([Descender Line](http://en.wikipedia.org/wiki/Descender))下。

1. UnderlineSpan —— 系统原生的下划线方式

2. UnderlineImageSpan —— 将文字转成图片，可以在任意TextView或EditText下灵活插入一块下划线文字。但是不支持换行。

3. UnderlineTextView —— 自定义TextView，onDraw绘制下划线。支持换行。


###注意：
由于下划线绘制在文字底部，需要设置 ```lineSpacingExtra``` 的行间距，才能有空余区域显示出来。

不能使用 ```lineSpacingMultiplier``` 来处理行高…… 不然会导致绘制不准确。


##Screenshot

![Screenshot](https://raw.githubusercontent.com/ongakuer/android-underline-fun/master/Screenshot.png "Screenshot")
