import java.math.BigInteger;
import java.util.ArrayList;

public class Chord {
    ArrayList<Node> nodeList;

    Chord() {
        nodeList = new ArrayList<>();
    }

    public void addNode(String nodeName, BigInteger i){
        Node node = new Node(nodeName, i);

        if(nodeList.isEmpty()) {
            node.join(null);
        }
        else {
            node.join(nodeList.get(0));
        }
        nodeList.add(node);
    }

    public void removeNode(Node nr) {
        // Node nr = nodeList.get(nodeName);
        Node s = nr.findSuccessor();
        Node p = nr.findPredecessor();

        s.moveKeys(nr, p.getNodeId().add(BigInteger.ONE), nr.getNodeId());

        s.setPredecessor(p);
        p.setSuccessor(s);


    }

    public void removeRandomNode() {
        // generate nodeID
        // Use random idx
        int idx = 0;
        Node nr = nodeList.get(idx);
        nodeList.remove(idx);
        removeNode(nr);
    }

    public void lookup(String key) {
        BigInteger id = new BigInteger(Util.hash(key), 16);
        Node succ = lookup(id);
        String value = succ.find(key);
        System.out.println(value);
    }

    public Node lookup(BigInteger id) {
        ArrayList<Node> path = new ArrayList<>();
        // Use random idx
        int idx = 0;
        Node n = nodeList.get(idx);
        Node s = n.findSuccessor(id, path);

        System.out.print("Lookup " + id + " : ");
        for(int i=0; i<path.size(); ++i) {
            Node pathNode = path.get(i);
            System.out.print(pathNode.getNodeId() + " -> ");
        }
        return s;
    }
}