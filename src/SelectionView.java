import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.TreeDragDropEvent;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "treeSelectionView")
@ViewScoped
public class SelectionView implements Serializable {

	private static final long serialVersionUID = -7226557829099674906L;
	private String newNode;

	public String getNewNode() {
		return newNode;
	}

	public void setNewNode(String newNode) {
		this.newNode = newNode;
	}

	private TreeNode root1;
	private TreeNode root3;
	private TreeNode selectedNode;
	private TreeNode[] selectedNodes2;

	@ManagedProperty("#{documentService}")
	private DocumentService service;

	@PostConstruct
	public void init() {
		root1 = service.createDocuments();
		root3 = service.createCheckboxDocuments();
	}

	public TreeNode getRoot1() {
		return root1;
	}

	public TreeNode getRoot3() {
		return root3;
	}

	public TreeNode getSelectedNode() {
		return selectedNode;
	}

	public void setSelectedNode(TreeNode selectedNode) {
		this.selectedNode = selectedNode;
	}

	public TreeNode[] getSelectedNodes2() {
		return selectedNodes2;
	}

	public void setSelectedNodes2(TreeNode[] selectedNodes2) {
		this.selectedNodes2 = selectedNodes2;
	}

	public void setService(DocumentService service) {
		this.service = service;
	}

	public void displaySelectedSingle() {
		if (selectedNode != null) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Selected", selectedNode.getData().toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void displaySelectedMultiple(TreeNode[] nodes) {
		if (nodes != null && nodes.length > 0) {
			StringBuilder builder = new StringBuilder();
			List<String> treeNodeNamesList = new ArrayList<String>();
			List<TreeNode> treeNodeList = new ArrayList<TreeNode>();
			treeNodeNamesList.add("root");
			treeNodeList.add(service.fetchTreeDefaultNodeObject("root", null));
			for (TreeNode node : nodes) {

				TreeNode currentNode = node;
				Stack<TreeNode> stackNodes = new Stack<TreeNode>();
				while (!currentNode.getData().toString().equals("root")) {
					if (!treeNodeNamesList.contains(currentNode.getData()
							.toString())) {

						stackNodes.push(currentNode);
						currentNode = currentNode.getParent();
					} else {
						break;
					}
				}
				while (!stackNodes.isEmpty()) {

					currentNode = stackNodes.pop();
					treeNodeNamesList.add(currentNode.getData().toString());
					treeNodeList
							.add(service.fetchTreeDefaultNodeObject(currentNode
									.getData().toString(), treeNodeList
									.get(treeNodeNamesList.indexOf(currentNode
											.getParent().getData().toString()))));
				}

				builder.append(node.getData().toString());
				builder.append("<br />");
			}
			root1 = treeNodeList.get(0);

			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
					"Selected", builder.toString());
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
	}

	public void onDragDrop(TreeDragDropEvent event) {
		TreeNode dragNode = event.getDragNode();
		TreeNode dropNode = event.getDropNode();
		int dropIndex = event.getDropIndex();

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
				"Dragged " + dragNode.getData(), "Dropped on "
						+ dropNode.getData() + " at " + dropIndex);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void deleteNode() {
		selectedNode.getChildren().clear();
		selectedNode.getParent().getChildren().remove(selectedNode);
		selectedNode.setParent(null);
		selectedNode = null;
	}

	public void addNode() {
		if (!newNode.equals(null) && !newNode.equals("")) {
			boolean nodePresent = false;
			for (int i = 0; i < selectedNode.getChildren().size(); i++) {
				if (selectedNode.getChildren().get(i).getData().toString()
						.equals(newNode)) {
					nodePresent = true;
					break;
				}
			}
			if (!nodePresent) {
				selectedNode.getChildren().add(
						service.fetchTreeDefaultNodeObject(newNode,
								selectedNode));
			}

		}
		newNode = null;
	}
}