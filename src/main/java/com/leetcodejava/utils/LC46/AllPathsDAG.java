package com.leetcodejava.utils.LC46;

/**
 * Created by wangdehao on 16/6/20.
 */



import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

/******************************************************************************
 *  Compilation:  javac AllPaths.java
 *  Execution:    java AllPaths
 *  Depedencies:  GraphDAG.java
 *
 *  Find all paths from s to t.
 *
 *  % java AllPaths
 *  A: B C
 *  B: A F
 *  C: A D F
 *  D: C E F G
 *  E: D G
 *  F: B C D
 *  G: D E
 *
 *  [A, B, F, C, D, E, G]
 *  [A, B, F, C, D, G]
 *  [A, B, F, D, E, G]
 *  [A, B, F, D, G]
 *  [A, C, D, E, G]
 *  [A, C, D, G]
 *  [A, C, F, D, E, G]
 *  [A, C, F, D, G]
 *
 *  [B, A, C, D, F]
 *  [B, A, C, F]
 *  [B, F]
 *
 *  Remarks
 *  --------
 *   -  Currently prints in reverse order due to stack toString()
 *
 ******************************************************************************/

public class AllPathsDAG<Vertex> {

    private Stack<String> path  = new Stack<String>();   // the current path
    private Set<String> onPath  = new TreeSet<String>();     // the set of vertices on the path
    private Set<String> patterns = new TreeSet<String>();
    private int count = 0;

    public AllPathsDAG(GraphDAG dag, String s, String t) {
        enumerate(dag, s, t);
    }
    public AllPathsDAG(GraphDAG dag) {
        generateAllPaths(dag);
        System.out.println("AllPathCount: " + patterns.size());
        for(String pattern : patterns){
            System.out.println(pattern);
        }
    }
    // use DFS
    private void enumerate(GraphDAG dag, String v, String t) {

        // add node v to current path from s
        path.push(v);
        onPath.add(v);

        // found path from s to t - currently prints in reverse order because of stack
        if (v.equals(t)){
            //System.out.println(path);
            String pattern = "";
            for(String s : path){
                pattern += s;
            }
            patterns.add(pattern);
        }


            // consider all neighbors that would continue path with repeating a node
        else {
            for (String w : dag.adjacentTo(v)) {
                if (!onPath.contains(w)) {
                    enumerate(dag, w, t);
                }
            }
        }

        // done exploring from v, so remove from path
        path.pop();
        onPath.remove(v);
    }

    public void generateAllPaths(GraphDAG dag){
        ArrayList<String> verticesArray = dag.getVerticesArray();
        for(int i = 0; i < verticesArray.size() - 1; i++){
            for(int j = i + 1; j < verticesArray.size(); j++){
                //System.out.println(i + " " +j);
                //System.out.println(verticesArray.get(i) + " " + verticesArray.get(j));
                enumerate(dag, verticesArray.get(i), verticesArray.get(j));
                enumerate(dag, verticesArray.get(j), verticesArray.get(i));

            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        //          page_name
        //| 151 | account_list                     | A           | 加载帐号列表页面                  |
        //| 152 | account_detail                   | B           | 加载帐号详情页面                  |
        //| 153 | launch_list                      | C           | 加载投放列表页面                  |
        //| 154 | plan_detail                      | D           | 加载计划详情页面                  |
        //| 155 | launch_detail                    | E           | 加载投放详情页面                  |
        //| 156 | ticket_list                      | F           | 加载工单列表页面                  |
        //| 157 | create_ticket                    | G           | 加载工单创建页面                  |
        //| 158 | update_ticket                    | H           | 加载工单修改页面                  |
        //| 159 | detail_report                    | I           | 加载数据页面                     |
        //| 160 | auto_distribution                | J           | 加载自动分配页面                  |
        //| 161 | call_record                      | K           | 加载电话记录页面                  |
        //| 162 | activity_statistic               | L           | 加载行为分析页面                  |


        // Homepages Set: A, F, I, J, K, L

        // Subpages Set: excluding path between homepages
        // A-B, A-D,
        // B-A, B-C, B-F, B-I, B-J, B-K, B-L
        // C-A, C-B, C-E, C-F, C-I, C-J, C-K, C-L
        // D-A, D-B, D-F, D-I, D-J, D-K, D-L
        // E-A, E-B, E-F, E-I, E-J, E-K, E-L
        // F-B, F-G, F-H
        // G-A, G-F, G-I, G-J, G-K, G-L
        // H-A, H-F, H-I, H-J, H-K, H-L
        // I-
        // J-
        // K-B, K-G
        // L-

        FileReader fr = new FileReader("/Users/wangdehao/Desktop/PageNavigationsConf");
        Scanner s = new Scanner(fr);
        GraphDAG dag = new GraphDAG(s, "-");
        System.out.println(dag);
        new AllPathsDAG(dag);
//        System.out.println(0.1+0.2);
    }

}

