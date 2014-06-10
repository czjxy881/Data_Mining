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
	/**
	 * 每个key对应的标号
	 */
	private HashMap<String, Key> KeyIndex;
	/**
	 * 如果连续的有小数改为double即可
	 */
	private Vector<Vector<Integer>> dataDetail; 
	/**
	 * Key的ID，其实应该放在Key类里用个static变量,0为分类
	 */
	private int KeyID;  
	/**
	 * 结果列的列名
	 */
	private String ClassC;
	public DataBase()
	{
		KeyIndex=new HashMap<String, Key>();
		dataDetail=new Vector<Vector<Integer>>();
		KeyID=0;
	}
	/**
	 * 设置结果列
	 * @param name 列名
	 * @param vs 可取值的集合 String数组
	 */
	public void Set_C(String name,String []vs)
	{
		if(KeyIndex.containsKey(name))return;
		KeyIndex.put(name,new Key(name,0,true,vs));
		ClassC=name;
	}
	/**
	 * 增加一列，即一个Key
	 * @param name 列名
	 * @param IsCategorical 是否为离散的
	 * @param vs 如果是离散的传入可取值的集合，否则传入Nul即可
	 * @return KeyID，列号
	 */
	public int Add_Key(String name,boolean IsCategorical,String[] vs)
	{
		if(KeyIndex.containsKey(name))return -1;
		KeyIndex.put(name,new Key(name,++KeyID,IsCategorical,vs));
		return KeyID;
	}
	/**
	 * 增加一行训练集数据
	 * @param date 一个存有数据的HashMap
	 */
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
	/**
	 * 获得在预期结果列为Class的条件下name列为value的概率，含拉普拉斯校准 
	 * @param name 列名
	 * @param value name列的值
	 * @param Class 结果列的预期值
	 * @return 概率
	 */
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
	/**
	 * 获得列的集合，Key名
	 * @return Key名的集合
	 */
	public Set<String> get_Keys()
	{
		return KeyIndex.keySet();
	}
	/**
	 * 获得一个Key可以取值的集合
	 * @param name Key名
	 * @return 可取值的集合
	 */
	public Set<String> get_Valuse(String name) 
	{
		return KeyIndex.get(name).get_Values();
	}
	/**
	 * 获得结果列
	 * @return Key类型的结果列
	 */
	public Key get_ClassC()
	{
		return KeyIndex.get(ClassC);
	}
	
}
	
	
	

