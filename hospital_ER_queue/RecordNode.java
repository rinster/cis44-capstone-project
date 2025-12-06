package hospital_ER_queue;

import java.util.ArrayList;
import java.util.List;

public class RecordNode {
    private String data;
    private List<RecordNode> children = new ArrayList<>();

    public RecordNode(String data) {
        this.data = data;
    }

    public void addChild(RecordNode child) {
        children.add(child);
    }

    public String show(String indent) {
        StringBuilder sb = new StringBuilder(indent + data + "\n");
        for (RecordNode child : children) {
            sb.append(child.show(indent + " "));
        }

        return sb.toString();
    }

}
