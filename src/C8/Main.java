package C8;

import java.util.HashMap;
/**
 * 测试类
 * @author jxy
 *
 */
public class Main {
	public static String[] names=new String[]{"age","income","student","credit_rating","buys_computer"};
	public static HashMap<String,String> init(String[] vs)
	{
		HashMap<String, String> now=new HashMap<String,String>();
		for(int i=0;i<vs.length;i++)
			now.put(names[i], vs[i]);
		return now;
	}
	
	public static void main(String[] args) {
		DataBase dataBase=new DataBase();
		dataBase.Set_C("buys_computer",new String[]{"yes","no"});
		dataBase.Add_Key("age", true,new String[]{"youth","middle_aged","senior"});
		dataBase.Add_Key("income", true,new String[]{"high","medium","low"});
		dataBase.Add_Key("student", true,new String[]{"yes","no"});
		dataBase.Add_Key("credit_rating", true,new String[]{"fair","excellent"});
		
		dataBase.Add_date(init(new String[]{"youth","high","no","fair","no"}));
		dataBase.Add_date(init(new String[]{"youth","high","no","excellent","no"}));
		dataBase.Add_date(init(new String[]{"middle_aged","high","no","fair","yes"}));
		dataBase.Add_date(init(new String[]{"senior","medium","no","fair","yes"}));
		dataBase.Add_date(init(new String[]{"senior","low","yes","fair","yes"}));
		dataBase.Add_date(init(new String[]{"senior","low","yes","excellent","no"}));
		dataBase.Add_date(init(new String[]{"middle_aged","low","yes","excellent","yes"}));
		dataBase.Add_date(init(new String[]{"youth","medium","no","fair","no"}));
		dataBase.Add_date(init(new String[]{"youth","low","yes","fair","yes"}));
		dataBase.Add_date(init(new String[]{"senior","medium","yes","fair","yes"}));
		dataBase.Add_date(init(new String[]{"youth","medium","yes","excellent","yes"}));
		dataBase.Add_date(init(new String[]{"middle_aged","medium","no","excellent","yes"}));
		dataBase.Add_date(init(new String[]{"middle_aged","high","yes","fair","yes"}));
		dataBase.Add_date(init(new String[]{"senior","medium","no","excellent","no"}));

		NavieBayesian navieBayesian=new NavieBayesian(dataBase);
		HashMap<String, String> date;String ans;
		
		date=init(new String[]{"youth","medium","yes","fair"});
		ans=navieBayesian.get_Class(date);
		System.out.println("预测数据:"+date.toString());
		System.out.println("预测结果:"+ans);
		
		date=init(new String[]{"youth","medium","no","fair"});
		ans=navieBayesian.get_Class(date);
		System.out.println("预测数据:"+date.toString());
		System.out.println("预测结果:"+ans);
		
	}

}
