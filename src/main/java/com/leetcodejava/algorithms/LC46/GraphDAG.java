package com.leetcodejava.algorithms.LC46;

import java.util.*;

/**
 * Created by wangdehao on 16/6/20.
 */
public class GraphDAG {


/******************************************************************************
 *  Compilation:  javac Graph.java
 *  Execution:    java Graph
 *  Dependencies: ST.java Set.java In.java System.out.java
 *
 *  Undirected graph data type implemented using a symbol table
 *  whose keys are vertices (String) and whose values are sets
 *  of neighbors (Set of Strings).
 *
 *  Remarks
 *  -------
 *   - Parallel edges are not allowed
 *   - Self-loop are allowed
 *   - Adjacency lists store many different copies of the same
 *     String. You can use less memory by interning the strings.
 *
 ******************************************************************************/

    /**
     *  The <tt>Graph</tt> class represents an undirected graph of vertices
     *  with string names.
     *  It supports the following operations: add an edge, add a vertex,
     *  get all of the vertices, iterate over all of the neighbors adjacent
     *  to a vertex, is there a vertex, is there an edge between two vertices.
     *  Self-loops are permitted; parallel edges are discarded.
     *  <p>
     *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/45graph">Section 4.5</a> of
     *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
     */

        // symbol table: key = string vertex, value = set of neighboring vertices

        private HashMap<String, Set<String>> st;

        // number of edges
        private int E;

        /**
         * Initializes an empty graph with no vertices or edges.
         */
        public GraphDAG() {
            st = new HashMap<String, Set<String>>();
        }

        /**
         * Initializes a graph from the specified input stream, using the specified delimiter.
         *
         * @param in the input stream
         * @param delimiter
         */
        public GraphDAG(Scanner in, String delimiter) {
            st = new HashMap<String, Set<String>>();
            while (in.hasNextLine()) {
                String line = in.nextLine();
                String[] names = line.split(delimiter);
                addEdge(names[0], names[1]);
            }
        }

        public ArrayList<String> getVerticesArray(){
            ArrayList<String> verticesArray = new ArrayList<String>();
            for(String vertex : st.keySet()){
                verticesArray.add(vertex);
            }
            return verticesArray;
        }

        /**
         * Returns the number of vertices in this graph.
         *
         * @return the number of vertices in this graph
         */
        public int V() {
            return st.size();
        }

        /**
         * Returns the number of edges in this graph.
         *
         * @return the number of edges in this graph
         */
        public int E() {
            return E;
        }

        // throw an exception if v is not a vertex
        private void validateVertex(String v) {
            if (!hasVertex(v)) throw new IllegalArgumentException(v + " is not a vertex");
        }

        /**
         * Returns the degree of vertex v in this graph.
         *
         * @param  v the vertex
         * @return the degree of <tt>v</tt> in this graph
         * @throws IllegalArgumentException if <tt>v</tt> is not a vertex in this graph
         */
        public int degree(String v) {
            validateVertex(v);
            return st.get(v).size();
        }

        /**
         * Adds the edge v-w to this graph (if it is not already an edge).
         *
         * @param  v one vertex in the edge
         * @param  w the other vertex in the edge
         */
        public void addEdge(String v, String w) {
            if (!hasVertex(v)) addVertex(v);
            if (!hasVertex(w)) addVertex(w);
            if (!hasEdge(v, w)) E++;
            st.get(v).add(w);
        }

        /**
         * Adds vertex v to this graph (if it is not already a vertex).
         *
         * @param  v the vertex
         */
        public void addVertex(String v) {
            if (!hasVertex(v)) st.put(v, new TreeSet<String>());
        }


        /**
         * Returns the vertices in this graph.
         *
         * @return the set of vertices in this graph
         */
        public Iterable<String> vertices() {
            return st.keySet();
        }

        /**
         * Returns the set of vertices adjacent to v in this graph.
         *
         * @param  v the vertex
         * @return the set of vertices adjacent to vertex <tt>v</tt> in this graph
         * @throws IllegalArgumentException if <tt>v</tt> is not a vertex in this graph
         */
        public Iterable<String> adjacentTo(String v) {
            validateVertex(v);
            return st.get(v);
        }

        /**
         * Returns true if v is a vertex in this graph.
         *
         * @param  v the vertex
         * @return <tt>true</tt> if <tt>v</tt> is a vertex in this graph,
         *         <tt>false</tt> otherwise
         */
        public boolean hasVertex(String v) {
            return st.keySet().contains(v);
        }

        /**
         * Returns true if v-w is an edge in this graph.
         *
         * @param  v one vertex in the edge
         * @param  w the other vertex in the edge
         * @return <tt>true</tt> if <tt>v-w</tt> is a vertex in this graph,
         *         <tt>false</tt> otherwise
         * @throws IllegalArgumentException if either <tt>v</tt> or <tt>w</tt>
         *         is not a vertex in this graph
         */
        public boolean hasEdge(String v, String w) {
            validateVertex(v);
            validateVertex(w);
            return st.get(v).contains(w);
        }

        /**
         * Returns a string representation of this graph.
         *
         * @return string representation of this graph
         */
        public String toString() {
            StringBuilder s = new StringBuilder();
            for (String v : st.keySet()) {
                s.append(v + ": ");
                for (String w : st.get(v)) {
                    s.append(w + " ");
                }
                s.append("\n");
            }
            return s.toString();
        }

        /**
         * Unit tests the <tt>Graph</tt> data type.
         */
        public static void main(String[] args) {
            GraphDAG G = new GraphDAG();
            //| 151 | account_list                     | A           | 加载帐号列表页面                  |
            //| 152 | account_detail                   | B           | 加载帐号详情页面                  |
            //| 153 | launch_list                      | C           | 加载投放列表页面                  |
            //| 154 | plan_detail                      | D           | 加载计划详情页面                  |
            //| 155 | launch_detail                    | E           | 加载投放详情页面                  |
            //| 156 | ticket_list                      | F           | 加载工单列表页面                  |
            //| 157 | create_ticket                    | G           | 加载工单创建页面                  |
            //| 158 | update_ticket                    | H           | 加载工单修改页面                  |
            //| 159 | detail_report                    | I           | 记载数据页面                     |
            //| 160 | auto_distribution                | J           | 加载自动分配页面                  |
            //| 161 | call_record                      | K           | 加载电话记录页面                  |
            //| 162 | activity_statistic               | L           | 加载行为分析页面                  |
            G.addEdge("A", "B");
            G.addEdge("A", "C");
            G.addEdge("C", "D");
            G.addEdge("D", "E");
            G.addEdge("D", "G");
            G.addEdge("E", "G");
            G.addVertex("H");

            // print out graph
            System.out.println(G);

            // print out graph again by iterating over vertices and edges
            //for (String v : G.vertices()) {
            //    System.out.print(v + ": ");
            //    for (String w : G.adjacentTo(v)) {
            //        System.out.print(w + " ");
            //    }
            //    System.out.println();
            //}

        }

    }



