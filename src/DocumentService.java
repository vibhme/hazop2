import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@ManagedBean(name = "documentService")
@ApplicationScoped
public class DocumentService {

	public TreeNode createDocuments() {

		TreeNode root = new DefaultTreeNode(
				new Document("root", "-", "Folder"), null);

		return root;
	}

	public TreeNode fetchTreeCheckBoxNodeObject(String data, TreeNode parent) {

		return new CheckboxTreeNode(new Document(data, "-", "Folder"), parent);
	}

	public TreeNode fetchTreeDefaultNodeObject(String data, TreeNode parent) {

		return new DefaultTreeNode(new Document(data, "-", "Folder"), parent);
	}

	public TreeNode createCheckboxDocuments() {
		List<String> treeNodeNamesList = new ArrayList<String>();
		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

		treeNodeNamesList.add("root");
		treeNodeNamesList.add("Drawing / References");
		treeNodeNamesList.add("Analysis");
		treeNodeNamesList.add("Deviation");
		treeNodeNamesList.add("Description");
		treeNodeNamesList.add("Causes");
		treeNodeNamesList.add("Consequence");
		treeNodeNamesList.add("Cause");
		treeNodeNamesList.add("Safeguard");
		treeNodeNamesList.add("Recommendation");
		treeNodeNamesList.add("Likelihood");
		treeNodeNamesList.add("Severity");
		treeNodeNamesList.add("New Formula");
		treeNodeNamesList.add("Risk Matrix");
		treeNodeNamesList.add("Document");
		treeNodeNamesList.add("File Attachment/Path");
		treeNodeNamesList.add("Revision Number");
		treeNodeNamesList.add("Comment");

		treeNodeList.add(fetchTreeCheckBoxNodeObject("root", null));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Drawing / References",
				treeNodeList.get(treeNodeNamesList.indexOf("root"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Analysis",
				treeNodeList.get(treeNodeNamesList.indexOf("root"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Deviation",
				treeNodeList.get(treeNodeNamesList.indexOf("root"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Description",
				treeNodeList.get(treeNodeNamesList.indexOf("Deviation"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Causes",
				treeNodeList.get(treeNodeNamesList.indexOf("Deviation"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Consequence",
				treeNodeList.get(treeNodeNamesList.indexOf("Deviation"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Cause",
				treeNodeList.get(treeNodeNamesList.indexOf("Causes"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Safeguard",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Recommendation",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Likelihood",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Severity",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("New Formula",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Risk Matrix ",
				treeNodeList.get(treeNodeNamesList.indexOf("Consequence"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Document", treeNodeList
				.get(treeNodeNamesList.indexOf("Drawing / References"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("File Attachment/Path",
				treeNodeList.get(treeNodeNamesList
						.indexOf("Drawing / References"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Revision Number",
				treeNodeList.get(treeNodeNamesList
						.indexOf("Drawing / References"))));
		treeNodeList.add(fetchTreeCheckBoxNodeObject("Comment", treeNodeList
				.get(treeNodeNamesList.indexOf("Drawing / References"))));

		return treeNodeList.get(0);
	}
}