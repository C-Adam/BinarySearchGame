public class BinaryNode {
   public String data;
   public BinaryNode yes_node;
   public BinaryNode no_node;

   //Makes the note with empty data
   public BinaryNode(String data) {
      this(data,null, null); 
   }
   
   // Makes a branch node with given data
   public BinaryNode(String data, BinaryNode yes_node, BinaryNode no_node) {
      this.data = data;
      this.yes_node = yes_node;
      this.no_node = no_node;
   }                      

}