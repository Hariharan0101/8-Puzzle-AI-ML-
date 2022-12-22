import java.io.*;
import java.util.*;
class 8_Puzzle
{
public static int n = 3;
public static class Node{
 Node parent;
 int mat[][] = new int[n][n];
 int a, b;
 int c;
 int l;
}
public static void printMatrix(int mat[][]){
 for(int i = 0; i < n; i++){
 for(int j = 0; j < n; j++){
 System.out.print(mat[i][j]+" ");
 }
 System.out.println("");
 }
}
public static Node newNode(
 int mat[][], int a, int b,
 int newX, int newY, int l,
 Node parent){
 Node node = new Node();
 node.parent = parent;
 node.mat = new int[n][n];
 for(int i = 0; i < n; i++){
 for(int j = 0; j < n; j++){
 node.mat[i][j] = mat[i][j];
 }
 }
 int temp = node.mat[a][b];
 node.mat[a][b] = node.mat[newX][newY];
 node.mat[newX][newY]=temp;
 node.c = Integer.MAX_VALUE;
 node.l = l;
 node.a = newX;
 node.b = newY;
 return node;
}
public static int row[] = { 1, 0, -1, 0 };
public static int col[] = { 0, -1, 0, 1 };
public static int calculateCost(int
startingMatrix[][], int goalMatrix[][]){
 int count = 0;
 for (int i = 0; i < n; i++)
 for (int j = 0; j < n; j++)
 if (startingMatrix[i][j]!=0 &&
startingMatrix[i][j] != goalMatrix[i][j])
 count++;
 return count;
}
public static int isSafe(int a, int b){
 return (a >= 0 && a < n && b >= 0 && b <
n)?1:0;
}
public static void printPath(Node root){
 if(root == null){
 return;
 }
 printPath(root.parent);
 printMatrix(root.mat);
 System.out.println("");
}
public static class comp implements
Comparator<Node>{
 @Override
 public int compare(Node lhs, Node rhs){
 return (lhs.c + lhs.l) > (rhs.c+rhs.l)?1:-1;
 }
}
public static void solve(int startingMatrix[][], int
a,
int b, int goalMatrix[][])
{
 PriorityQueue<Node> t = new
PriorityQueue<>(new comp());
 Node root = newNode(startingMatrix, a, b, a,
b, 0, null);
 root.c =
calculateCost(startingMatrix,goalMatrix);
 t.add(root);
 while(!t.isEmpty()){
 Node min = t.peek();
 c
 t;poll();
 if(min.c == 0){
 printPath(min);
 return;
 }
 for (int i = 0; i < 4; i++){
 if (isSafe(min.a + row[i], min.b +
col[i])>0){
 Node child = newNode(min.mat, min.a,
min.b, min.a +
 row[i],min.b + col[i], min.l + 1, min);
 child.c = calculateCost(child.mat,
goalMatrix);
 t.add(child);
 }
 }
 }
}
public static void main (String[] args){
 int startingMatrix[][] ={
 {1, 2, 3},
 {5, 6, 0},
 {7, 8, 4}
 };
 int goalMatrix[][] ={
 {1, 2, 3},
 {5, 8, 6},
 {0, 7, 4}
 };
 int a = 1, b = 2;
 solve(startingMatrix, a, b, goalMatrix);
}
}