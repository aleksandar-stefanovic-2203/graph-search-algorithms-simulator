package simulation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import utilities.exception.*;

public class NodeTest {
	
	@Test
	void createNodeNullName() {
		assertThrows(NullArgumentException.class, () -> new Node(null, -1, -1));
	}
	
	@Test
	void createNodeBlankName() {
		assertThrows(BlankArgumentException.class, () -> new Node("  ", -1, -1));
	}
	
	@Test
	void getPath() {
		Node root = new Node("A", -1, -1);
		Node child1 = new Node("B", -1, -1);
		Node child2 = new Node("C", -1, -1);
		Node child3 = new Node("D", -1, -1);
		
		root.addChild(child1);
		root.addChild(child2);
		child2.addChild(child3);
		
		List<Node> path = child3.getPath();
		assertEquals(3, path.size());
		assertEquals("A", path.get(0).getName());
		assertEquals("C", path.get(1).getName());
		assertEquals("D", path.get(2).getName());
	}
	
	@Test
	void getChildrenReturnsUnmodifiableList() {
		Node root = new Node("A", -1, -1);
		Node child1 = new Node("B", -1, -1);
		Node child2 = new Node("C", -1, -1);
		Node child3 = new Node("D", -1, -1);
		
		root.addChild(child1);
		root.addChild(child2);
		root.addChild(child3);
		
		List<Node> children = root.getChildren();
		assertThrows(UnsupportedOperationException.class, () -> children.clear());
	}
	
	@Test
	void cloneTree() {
		Node root = new Node("A", 1, 4);
		Node child1 = new Node("B", -1, -1);
		Node child2 = new Node("C", 2, 6);
		Node child3 = new Node("D", 3, 7);
		
		root.addChild(child1);
		root.addChild(child2);
		child2.addChild(child3);
		
		Node copy = root.cloneTree();
		
		assertTreeEquals(root, copy);
	}

	private static void assertTreeEquals(Node root, Node copy) {
		assertNotEquals(root, copy);
		
		assertEquals(root.getName(), copy.getName());
		assertEquals(root.getSeqNum(), copy.getSeqNum());
		assertEquals(root.getValue(), copy.getValue());
		
		for(int i = 0; i < root.getChildren().size(); i++) {
			assertTreeEquals(root.getChildren().get(i), copy.getChildren().get(i));
		}
	}
	
}
