package C8;

import java.util.HashMap;
import java.util.Set;

/**
 * 类别类，Name为列名，ID为对应的编号用于数据紧密存储，IsCategoriacl是否离散
 * @author jxy
 *
 */
public class Key {
	/**
	 * 列名
	 */
	private String Name;
	/**
	 * 列的ID编号
	 */
	private int ID;
	/**
	 * 是否离散
	 */
	private boolean IsCategorical;
	/**
	 * 可取值的集合，如果为离散，则这个变量无用
	 */
	private HashMap<String, Integer> Values;
	/**
	 * 每个值出现的次数
	 */
	private HashMap<Integer,Integer> Count; 
	/**
	 * 这个列出现的总次数
	 */
	private int CountAll;
	public Key(String name,int id,boolean IsC)
	{
		this(name, id, IsC,null);
	}
	/**
	 * 构造函数，如果IsC为false，vs强制为null
	 * @param name 列名
	 * @param id 编号
	 * @param IsC 是否离散
	 * @param vs 可取的集合,只有初始化时可以赋值
	 */
	public Key(String name,int id,boolean IsC,String []vs)
	{
		Name=name;
		ID=id;
		IsCategorical=IsC;
		if(IsC==false)vs=null;
		Values=new HashMap<String,Integer>();
		Count=new HashMap<Integer,Integer>();
		add_value(vs);
		CountAll=0;
	}
	/**
	 * 增加一组可取值
	 * @param vs 可取值的集合
	 */
	private void add_value(String[] vs)
	{
		if(vs==null)return;
		for(String v:vs)
		{
			add_value(v);
		}
	}
	/**
	 * 增加一个可取值
	 * @param v 可取值
	 */
	private void add_value(String v)
	{
		if(Values.containsKey(v))return;
		Values.put(v, Values.size()+1);
		Count.put(Values.size(), 0);
	}
	public String get_name()
	{
		return Name;
	}
	public int get_id()
	{
		return ID;
	}
	/**
	 * 获得这个可取值对应的内部编号
	 * @param v 可取值的Strig
	 * @return	内部编号
	 */
	public int get_value(String v)
	{
		return Values.get(v);
	}
	public boolean isCategorical()
	{
		return IsCategorical;
	}
	/**
	 * 增加一个i的计数
	 * @param i 可取值的内部编号
	 */
	public void add_Count(int i)
	{
		Count.put(i, Count.get(i)+1);
		CountAll++;
	}
	public int get_Count(String s)
	{
		return Count.get(Values.get(s));
	}
	public int get_SUM()
	{
		return CountAll;
	}
	public Set<String> get_Values()
	{
		return Values.keySet();
	}
}
