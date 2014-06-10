package C8;

import java.util.HashMap;
/**
 * 朴素贝叶斯算法
 * @author jxy
 *
 */
public class NavieBayesian {
	DataBase dataBase;
	Key ClassC;
	public NavieBayesian(DataBase ds)
	{
		dataBase=ds;
		ClassC=ds.get_ClassC();
	}
	/**
	 * 传入待分类元组，得到分类结果
	 * @param date 元祖的HashMap
	 * @return 分类结果
	 */
	public String get_Class(HashMap<String, String> date)
	{
		String ans=null;
		double ratio=0.0;
		for(String C:ClassC.get_Values())
		{
			double P=ClassC.get_Count(C)/((double)ClassC.get_SUM());
			for(String k:date.keySet()) //套公式而已
			{
				if(k.equals(ClassC.get_name()))continue;
				P*=dataBase.get_ratio(k, date.get(k),C);
			}
			if(P>ratio){
				ratio=P;
				ans=C;
			}
		}
		return ans;
	}
}
