package C8;

import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
/**
 * 数据集
 * @author jxy
 *
 */

public class DataBase {
	private HashMap<String, Key> KeyIndex; //每个key对应的标号
	private Vector<Vector<Integer>> dataDetail; //如果连续的有小数改为double即可
	private int KeyID;  //Key的ID，其实应该放在Key类里用个static变量,0为分类
	private String ClassC;
	public DataBase()
	{
		KeyIndex=new HashMap<String, Key>();
		dataDetail=new Vector<Vector<Integer>>();
		KeyID=0;
	}
	public void Set_C(String name,String []vs)
	{
		if(KeyIndex.containsKey(name))return;
		KeyIndex.put(name,new Key(name,0,true,vs));
		ClassC=name;
	}
	public int Add_Key(String name,boolean IsCategorical,String[] vs)
	{
		if(KeyIndex.containsKey(name))return -1;
		KeyIndex.put(name,new Key(name,++KeyID,IsCategorical,vs));
		return KeyID;
	}
	public void Add_date(HashMap<String, String> date)
	{
		Vector<Integer> ndate=new Vector<Integer>();
		ndate.setSize(KeyID+1);
		for(String k:date.keySet())
		{
			Key key=KeyIndex.get(k);
			int value;  //如连续的有小数改为double即可
			if(key.isCategorical()==true){
				value=key.get_value(date.get(k));
				key.add_Count(value);
			}
			else{
				value=Integer.valueOf(date.get(k));
			}
			ndate.set(key.get_id(), value);
		}
		dataDetail.add(ndate);
	}
	public double get_ratio(String name,String value,String Class) 
	{
		//TODO: 连续的为另一种情况
		int a=0,b=KeyIndex.get(ClassC).get_Count(Class);
		int aim=KeyIndex.get(name).get_value(value),aimC=KeyIndex.get(ClassC).get_value(Class);
		int id=KeyIndex.get(name).get_id();
		for(int i=0;i<dataDetail.size();i++)
		{
			//分类列id为0，目标列为id
			if(dataDetail.get(i).get(0)==aimC&&dataDetail.get(i).get(id)==aim){
				a++;
			}
		}
		if(a==0){a++;b++;}//拉普拉斯校准
		return a/((double)b);
	}
	public Set<String> get_Keys()
	{
		return KeyIndex.keySet();
	}
	public Set<String> get_Valuse(String name) 
	{
		return KeyIndex.get(name).get_Values();
	}
	public Key get_ClassC()
	{
		return KeyIndex.get(ClassC);
	}
	
}
	
	
	

