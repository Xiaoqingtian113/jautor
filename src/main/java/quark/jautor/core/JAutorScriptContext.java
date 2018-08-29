package quark.jautor.core;

import java.util.Map.Entry;
import java.util.Set;

import javax.script.Bindings;
import javax.script.SimpleScriptContext;

public class JAutorScriptContext extends SimpleScriptContext{

	@Override
	public String toString() {
		Bindings binds = getBindings(ENGINE_SCOPE);
		Set<Entry<String,Object>> entrys = binds.entrySet();
		StringBuffer sb = new StringBuffer();
		sb.append("变量：");
		for(Entry<String,Object> entry : entrys){
			sb.append("{" + entry.getKey() + ":" + entry.getValue() + "}");
		}
		return sb.toString();
	}
}
