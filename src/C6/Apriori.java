package C6;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;
/**
 * Apriori算法，传入最小支持度阀值和DataBase模式的数据仓库
 * output获得结果
 * Public 两个成员变量，num为结果Vector，list_num为频繁项集的最高项数目
 * @author jxy
 *
 */

public class Apriori {
	private double MIN_SUP;//最小支持度阀值
	private DataBase Data; //源数据
	private int Max_ID;
	public int List_Num;
	public Vector<HashMap<BitSet, Integer> > num;
	/**
	 * 构造函数
	 * @param min_sup 最小支持度阀值
	 * @param dataBase 数据仓库
	 */
	public Apriori(int min_sup,DataBase dataBase)
	{
		Data=dataBase;
		Max_ID=Data.maxID();
		num=new Vector<HashMap<BitSet, Integer> >(Data.size());
		MIN_SUP=min_sup;
		solve();
	}
	/**
	 * 查找一个项集的支持度
	 * @param now BitSet形式的项集
	 * @return 支持度
	 */
	private int find(BitSet now)
	{
		int ans=0;
		for(int i=0;i<Data.size();i++)
		{
			BitSet t=(BitSet) now.clone();
			t.and(Data.list().get(i).set()); //应该Data实现Iterator接口
			if(t.equals(now))
			{
				ans++;
				//TODO: 如果需要增加每次出现的TID号则在这里增加
			}
		}
		return ans;
	}
	@SuppressWarnings("unchecked")
	private void solve()
	{
		HashMap<BitSet, Integer> now=new HashMap<BitSet, Integer>();
		for(int i=1;i<=Max_ID;i++) //找出为项为1的频繁项集
		{
			BitSet t=new BitSet();
			t.set(i);
			int nn=find(t);
			if(nn>=MIN_SUP)now.put(t, nn);
		}
		num.add(null);
		num.add(1,(HashMap<BitSet, Integer>) now.clone()); //必须clone，当心浅复制
		int last=1;
		while(last<Max_ID&&num.size()>last&&num.get(last).size()!=0)
		{
			now.clear();
			Set<BitSet> lastSet=num.get(last).keySet();
			BitSet []lastList=new BitSet[lastSet.size()];
			lastSet.toArray(lastList);
			int len=lastList.length;
			for(int i=0;i<len;i++)
			{
				int ca=lastList[i].cardinality()+1;
				for(int j=i;j<len;j++)
				{
					BitSet t=(BitSet) lastList[j].clone();
					t.or(lastList[i]);
					int ct=t.cardinality();
					//相差不为一项或者已有重复，跳过
					if(ct!=ca||ct!=lastList[j].cardinality()+1||now.containsKey(t))continue;
					int nn=find(t);
					if(nn>=MIN_SUP)now.put(t, nn);
				}
			}
			num.add((HashMap<BitSet, Integer>) now.clone());
			last++;
		}
		List_Num=last-1;
	}
	public void output()
	{
		System.out.println("在最小支持阀度为"+String.valueOf(MIN_SUP)+"的情况下:");
		for(int i=1;i<=List_Num;i++)
		{
			System.out.println("    "+String.valueOf(i)+"项的频繁项集为:"+num.get(i).toString());
		}
	}
}
