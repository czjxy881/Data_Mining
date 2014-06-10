package C10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



/**
 * 测试类
 * @author jxy
 *
 */
public class Main {

	public static void main(String[] args) {
		Graph graph=new Graph(8);
		
		/*文件读入*/
		File file=new File("./src/C10/1.txt");
		try {
			Scanner scanner=new Scanner(file);
			/*
			while(scanner.hasNext())
			{
				graph.add_Edge(scanner.nextInt(),scanner.nextInt() );
			}
			*/
			
			graph.add_Edge(1,2);
			graph.add_Edge(1,3);
			graph.add_Edge(2,3);
			graph.add_Edge(3,5);
			graph.add_Edge(4,5);
			graph.add_Edge(4,6);
			graph.add_Edge(5,6);
			graph.add_Edge(5,8);
			graph.add_Edge(6,7);
			/*
			graph.add_Edge(1,2,45);
			graph.add_Edge(1,3,4);
			graph.add_Edge(2,3,30);
			graph.add_Edge(3,5,1);
			graph.add_Edge(4,5,20);
			graph.add_Edge(4,6,25);
			graph.add_Edge(5,6,23);
			graph.add_Edge(5,8,5);
			graph.add_Edge(6,7,7);
			*/
			GN gn=new GN(graph);
			gn.run();
			gn.output();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
			
		}
		
		
		
		
		

	}

}
