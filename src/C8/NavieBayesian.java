package C8;

import java.util.HashMap;

public class NavieBayesian {
	DataBase dataBase;
	Key ClassC;
	public NavieBayesian(DataBase ds)
	{
		dataBase=ds;
		ClassC=ds.get_ClassC();
	}
	public String get_Class(HashMap<String, String> date)
	{
		String ans=null;
		double ratio=0.0;
		for(String C:ClassC.get_Values())
		{
			double P=ClassC.get_Count(C)/((double)ClassC.get_SUM());
			for(String k:date.keySet())
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
