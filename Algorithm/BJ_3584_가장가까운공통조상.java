import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class BJ_3584_가장가까운공통조상 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int nodeNum = sc.nextInt();
            for (int j = 0; j < nodeNum; j++) {

            }
        }
    }

    class Node {
        private int id;
        private Integer parentId; // 부모 ID, 루트 노드의 경우 null 일 수 있음
        private List<Integer> childrenIds; // 자식 노드 ID 목록

        public Node(int id, Integer parentId) {
            this.id = id;
            this.parentId = parentId;
            this.childrenIds = new ArrayList<>();
        }

        public void addChildId(int childId) {
            childrenIds.add(childId);
        }

        public int getId() {
            return id;
        }

        public Integer getParentId() {
            return parentId;
        }

        public List<Integer> getChildrenIds() {
            return childrenIds;
        }
    }

    class Tree {
        private Map<Integer, Node> nodes;

        public Tree() {
            nodes = new HashMap<>();
        }

        public void addNode(Node node) {
            nodes.put(node.getId(), node);
            if (node.getParentId() != null) {
                Node parent = nodes.get(node.getParentId());
                if (parent != null) {
                    parent.addChildId(node.getId());
                }
            }
        }

        public Node getNode(int nodeId) {
            return nodes.get(nodeId);
        }

        public Node getParentNode(int childId) {
            Node child = getNode(childId);
            if (child == null || child.getParentId() == null) {
                return null;
            }
            return getNode(child.getParentId());
        }
    }
}
