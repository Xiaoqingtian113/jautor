package quark.jautor.util;

import java.util.Random;

public class RandomUtil {
	public static String getName() {
		String[] firsname = { "赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨", "朱", "秦",
				"尤", "许", "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦",
				"章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎", "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳",
				"酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷", "罗", "毕", "郝", "邬", "安", "常", "乐",
				"于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和", "穆", "萧", "尹", "姚", "邵",
				"湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
				"屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闽", "席", "季", "麻", "强", "贾", "路", "娄", "危", "江", "童", "颜", "郭",
				"梅", "盛", "林", "刁", "锺", "徐", "丘", "骆", "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍", "虞", "万", "支", "柯", "昝",
				"管", "卢", "莫", "经", "房", "裘", "缪", "干", "解", "应", "宗", "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪", "包", "诸",
				"左", "石", "崔", "吉", "钮", "龚", "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁", "荀", "羊", "於", "惠", "甄", "麹", "家",
				"封", "芮", "羿", "储", "靳", "汲", "邴", "糜", "松", "井", "段", "富", "巫", "乌", "焦", "巴", "弓", "牧", "隗", "山", "谷",
				"车", "侯", "宓", "蓬", "全", "郗", "班", "仰", "秋", "仲", "伊", "宫", "宁", "仇", "栾", "暴", "甘", "斜", "厉", "戎", "祖",
				"武", "符", "刘", "景", "詹", "束", "龙", "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄", "印", "宿", "白", "怀", "蒲", "邰",
				"从", "鄂", "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙", "池", "乔", "阴", "郁", "胥", "能", "苍", "双", "闻", "莘", "党",
				"翟", "谭", "贡", "劳", "逄", "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍", "郤", "璩", "桑", "桂", "濮", "牛", "寿", "通",
				"边", "扈", "燕", "冀", "郏", "浦", "尚", "农", "温", "别", "庄", "晏", "柴", "瞿", "阎", "充", "慕", "连", "茹", "习", "宦",
				"艾", "鱼", "容", "向", "古", "易", "慎", "戈", "廖", "庾", "终", "暨", "居", "衡", "步", "都", "耿", "满", "弘", "匡", "国",
				"文", "寇", "广", "禄", "阙", "东", "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆", "师", "巩", "厍", "聂", "晁", "勾", "敖",
				"融", "冷", "訾", "辛", "阚", "那", "简", "饶", "空", "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰", "巢", "关", "蒯", "相",
				"查", "后", "荆", "红", "游", "竺", "权", "逑", "盖", "益", "桓", "公", "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛", "闻人",
				"东方", "赫连", "皇甫", "尉迟", "公羊", "澹台", "公冶", "宗政", "濮阳", "淳于", "单于", "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐",
				"锺离", "宇文", "长孙", "慕容", "鲜于", "闾丘", "司徒", "司空", "丌官", "司寇", "仉", "督", "子车", "颛孙", "端木", "巫马", "公西",
				"漆雕", "乐正", "壤驷", "公良", "拓拔", "夹谷", "宰父", "谷梁", "晋", "楚", "阎", "法", "汝", "鄢", "涂", "钦", "段干", "百里",
				"东郭", "南门", "呼延", "归", "海", "羊舌", "微生", "岳", "帅", "缑", "亢", "况", "后", "有", "琴", "梁丘", "左丘", "东门", "西门",
				"商", "牟", "佘", "佴", "伯", "赏", "南宫", "墨", "哈", "谯", "笪", "年", "爱", "阳", "佟" };
		String[] namelist = { "伟", "伟", "芳", "伟", "秀英", "秀英", "娜", "秀英", "伟", "敏", "静", "丽", "静", "丽", "强", "静", "敏",
				"敏", "磊", "军", "洋", "勇", "勇", "艳", "杰", "磊", "强", "军", "杰", "娟", "艳", "涛", "涛", "明", "艳", "超", "勇", "娟",
				"杰", "秀兰", "霞", "敏", "军", "丽", "强", "平", "刚", "杰", "桂英", "芳", "　嘉懿", "煜城", "懿轩", "烨伟", "苑博", "伟泽", "熠彤",
				"鸿煊", "博涛", "烨霖", "烨华", "煜祺", "智宸", "正豪", "昊然", "明杰", "立诚", "立轩", "立辉", "峻熙", "弘文", "熠彤", "鸿煊", "烨霖",
				"哲瀚", "鑫鹏", "致远", "俊驰", "雨泽", "烨磊", "晟睿", "天佑", "文昊", "修洁", "黎昕", "远航", "旭尧", "鸿涛", "伟祺", "荣轩", "越泽",
				"浩宇", "瑾瑜", "皓轩", "擎苍", "擎宇", "志泽", "睿渊", "楷瑞", "子轩", "弘文", "哲瀚", "雨泽", "鑫磊", "修杰", "伟诚", "建辉", "晋鹏",
				"天磊", "绍辉", "泽洋", "明轩", "健柏", "鹏煊", "昊强", "伟宸", "博超", "君浩", "子骞", "明辉", "鹏涛", "炎彬", "鹤轩", "越彬", "风华",
				"靖琪", "明诚", "高格", "光华", "国源", "冠宇", "晗昱", "涵润", "翰飞", "翰海", "昊乾", "浩博", "和安", "弘博", "宏恺", "鸿朗", "华奥",
				"华灿", "嘉慕", "坚秉", "建明", "金鑫", "锦程", "瑾瑜", "晋鹏", "经赋", "景同", "靖琪", "君昊", "俊明", "季同", "开济", "凯安", "康成",
				"乐语", "力勤", "良哲", "理群", "茂彦", "敏博", "明达", "朋义", "彭泽", "鹏举", "濮存", "溥心", "璞瑜", "浦泽", "奇邃", "祺祥", "荣轩",
				"锐达", "睿慈", "绍祺", "圣杰", "晟睿", "思源", "斯年", "泰宁", "天佑", "同巍", "奕伟", "祺温", "文虹", "向笛", "心远", "欣德", "新翰",
				"兴言", "星阑", "修为", "旭尧", "炫明", "学真", "雪风", "雅昶", "阳曦", "烨熠", "英韶", "永贞", "咏德", "宇寰", "雨泽", "玉韵", "越彬",
				"蕴和", "哲彦", "振海", "正志", "子晋", "自怡", "德赫", "君平" };
		int a = (int) (firsname.length * Math.random());
		int b = (int) (namelist.length * Math.random());
		String name = firsname[a] + namelist[b];
		return name;
	}

	public static String getRandomString(int len) {
		String base = "天地玄黄宇宙洪荒日月盈昃辰宿列张寒来暑往秋收冬藏闰馀成岁律吕调阳云腾致雨露结为霜金生丽水玉出"
				+ "昆冈剑号巨阙珠称夜光果珍李柰菜重芥姜海咸河淡鳞潜羽翔龙师火帝鸟官人皇始制文字乃服衣裳推位让国有虞陶"
				+ "唐吊民伐罪周发殷汤坐朝问道垂拱平章爱育黎首臣伏戎羌遐迩壹体率宾归王鸣凤在竹白驹食场化被草木赖及万方"
				+ "盖此身发四大五常恭惟鞠养岂敢毁伤女慕贞洁男效才良知过必改得能莫忘罔谈彼短靡恃己长信使可覆器欲难量墨"
				+ "悲丝染诗赞羔羊景行维贤克念作圣德建名立形端表正空谷传声虚堂习听祸因恶积福缘善庆尺璧非宝寸阴是竞资父"
				+ "事君曰严与敬孝当竭力忠则尽命临深履薄夙兴温凊似兰斯馨如松之盛川流不息渊澄取映容止若思言辞安定笃初诚"
				+ "美慎终宜令荣业所基籍甚无竟学优登仕摄职从政存以甘棠去而益咏乐殊贵贱礼别尊卑上和下睦夫唱妇随外受傅训"
				+ "入奉母仪诸姑伯叔犹子比儿孔怀兄弟同气连枝交友投分切磨箴规仁慈隐恻造次弗离节义廉退颠沛匪亏性静情逸心"
				+ "动神疲守真志满逐物意移坚持雅操好爵自縻都邑华夏东西二京背邙面洛浮渭据泾宫殿盘郁楼观飞惊图写禽兽画彩"
				+ "仙灵丙舍傍启甲帐对楹肆筵设席鼓瑟吹笙升阶纳陛弁转疑星右通广内左达承明既集坟典亦聚群英杜稿钟隶漆书壁"
				+ "经府罗将相路侠槐卿户封八县家给千兵高冠陪辇驱毂振缨世禄侈富车驾肥轻策功茂实勒碑刻铭番溪伊尹佐时阿衡"
				+ "奄宅曲阜微旦孰营桓公匡合济弱扶倾绮回汉惠说感武丁俊义密勿多士实宁晋楚更霸赵魏困横假途灭虢践土会盟何"
				+ "遵约法韩弊烦刑起翦颇牧用军最精宣威沙漠驰誉丹青九州禹迹百郡秦并岳宗泰岱禅主云亭雁门紫塞鸡田赤城昆池"
				+ "碣石巨野洞庭旷远绵邈岩岫杳冥治本于农务兹稼穑叔载南亩我艺黍稷税熟贡新劝赏黜陟孟轲敦素史鱼秉直庶几中"
				+ "庸劳谦谨敕聆音察理鉴貌辨色贻厥嘉猷勉其祗植省躬讥诫宠增抗极殆辱近耻林皋幸即两疏见机解组谁逼索居闲处"
				+ "沉默寂寥求古寻论散虑逍遥欣奏累遣戚谢欢招渠荷的历园莽抽条枇杷晚翠梧桐蚤凋陈根委翳落叶飘摇游昆独运凌"
				+ "摩绛霄耽读玩市寓目囊箱易酋攸畏属耳垣墙具膳餐饭适口充肠饱饫烹宰饥厌糟糠亲戚故旧老少异粮妾御绩纺侍巾"
				+ "帷房纨扇圆洁银烛炜煌昼眠夕寐蓝笋象床弦歌酒宴接杯举觞矫手顿足悦豫且康嫡后嗣续祭祀蒸尝稽颡再拜悚惧恐"
				+ "惶笺牒简要顾答审详骸垢想浴执热愿凉驴骡犊特骇跃超骧诛斩贼盗捕获叛亡布射僚丸嵇琴阮啸恬笔伦纸钧巧任钓"
				+ "释纷利俗并皆佳妙毛施淑姿工颦妍笑年矢每催曦晖朗曜璇玑悬斡晦魄环照指薪修祜永绥吉劭矩步引领俯仰廊庙束"
				+ "带矜庄徘徊瞻眺孤陋寡闻愚蒙等诮谓语助者焉哉乎也";
		Random random = new Random();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int last = random.nextInt(base.length());
			str.append(base.charAt(last));
		}
		return str.toString();
	}

	public static String getBankCard(String bank_name) {
		String prefix = null;
		int suffix = 0;
		switch (bank_name) {
		case "工商银行":// 中国工商银行
			prefix = "621300";
			suffix = 16 - prefix.length();
			break;
		case "建设银行":// 中国建设银行
			prefix = "621700";
			suffix = 19 - prefix.length();
			break;
		case "农业银行":// 中国农业银行
			prefix = "955989160";
			suffix = 19 - prefix.length();
			break;
		case "光大银行":// 中国光大银行
			prefix = "900302";
			suffix = 16 - prefix.length();
			break;
		case "中国银行":// 中国银行
			prefix = "621661";
			suffix = 19 - prefix.length();
			break;
		case "兴业银行":// 兴业银行
			prefix = "622909";
			suffix = 18 - prefix.length();
			break;
		case "中信银行":// 中信银行
			prefix = "433670";
			suffix = 16 - prefix.length();
			break;
		case "平安银行":// 平安银行
			prefix = "622298";
			suffix = 16 - prefix.length();
			break;
		case "交通银行":// 交通银行
			prefix = "622258";
			suffix = 17 - prefix.length();
			break;
		case "民生银行":// 中国民生银行
			prefix = "415599";
			suffix = 16 - prefix.length();
			break;
		case "广发银行":// 广发银行
			prefix = "623259";
			suffix = 19 - prefix.length();
			break;
		case "招商银行":// 招商银行
			prefix = "690755";
			suffix = 15 - prefix.length();
			break;
		case "邮政储蓄":// 中国邮政储蓄银行
			prefix = "620062";
			suffix = 19 - prefix.length();
			break;
		default:
		}
		for (int j = 0; j < suffix; j++)
			prefix = prefix + (int) Math.rint(Math.random() * 10);
		return prefix;
	}

	public static String getPhoneNumber() {
		return "140" + getRandomNumber(8);
	}

	public static String getRandomNumber(int len) {
		String base = "0123456789";
		Random random = new Random();
		StringBuffer str = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(base.length());
			str.append(base.charAt(number));
		}
		return str.toString();
	}
}