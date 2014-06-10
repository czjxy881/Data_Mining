package C10;

import java.util.Vector;
/**
 * GN算法，信息中心度参考论文10.1103/PhysRevE.70.056104
 * @author jxy
 *
 */
public class GN {
	private Graph graph;
	/**
	 * 建图
	 * @param g 分类图
	 */
	public GN(Graph g)
	{
		graph=g;
	}
	/**
	 * 主算法，含Modularity剪枝
	 * @return 划分结果的块数
	 */
	public int run()
	{
		double Q_Last=0; //上一次的Q值
		int T=0;
		int SumEdge=graph.get_EdgeNum();
		while(graph.get_EdgeNum()!=0){
			int m=graph.get_E();
			graph.test_del(m); //测试删除后的结果
			graph.paint(); //染色
			double Q_now=0;
			Vector<int[]>numC=graph.get_CNum();
			for(int i=1;i<numC.size();i++){ //获得内外边
				int []now=numC.get(i);
				double t=(2*now[0]-now[1])/(2.0*SumEdge);
				Q_now+=(now[0]/(double)SumEdge+t*t);
			}
			graph.resume_test(m); //恢复删除
			System.out.println(String.valueOf(T++)+" Last:"+String.valueOf(Q_Last)+" Now:"+String.valueOf(Q_now));
			if(Q_now<=Q_Last){
				break;
			}
			else{
				graph.del_Edge(m); //真实删除
			}	
			Q_Last=Q_now;
		}
		graph.paint();
		return graph.get_divNum();
	}
	/**
	 * 输出，可自定义
	 */
	public void output(){
		int num=graph.get_divNum();
		System.out.println("经过划分，分成"+String.valueOf(num)+"类:");
		int[] Colors=graph.get_Paint();
		for(int i=1;i<=num;i++){
			System.out.print("第"+String.valueOf(i)+"类: ");
			for(int j=0;j<Colors.length;j++){
				if(Colors[j]==i){
					System.out.print(String.valueOf(j+1)+" ");
				}
			}
			System.out.println();
		}
	}
}
