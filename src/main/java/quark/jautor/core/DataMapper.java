package quark.jautor.core;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.tree.DefaultMutableTreeNode;

public class DataMapper {
	public static Map<String, DefaultMutableTreeNode> mapNode = new HashMap<String, DefaultMutableTreeNode>();
	public static Map<String, POData> mapPo = new HashMap<String, POData>();
	public static Properties prop;
}
