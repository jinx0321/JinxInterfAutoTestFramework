package AutoTest.Regex;

import java.util.Map;

/**
 * ���ʽ����ģ��
 * 
 * @author jinxh29224
 *
 */
public class RegexUpdateModel {

	//�Ƿ��������
	private boolean is_over = false;
	
	//���ʽ�Ƿ�Ԥ����
	private boolean is_pre =false;

	private String content;
	
	private Map<String,RegexUpdateModel> contentexp;
	
	public RegexUpdateModel(String var) {
		content=var;
	}

	public boolean isIs_over() {
		return is_over;
	}

	public void setIs_over(boolean is_over) {
		this.is_over = is_over;
	}

	public boolean isIs_pre() {
		return is_pre;
	}

	public void setIs_pre(boolean is_pre) {
		this.is_pre = is_pre;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Map<String,RegexUpdateModel> getContentexp() {
		return contentexp;
	}

	public void setContentexp(Map<Object, Object> collect) {
		this.contentexp = (Map)collect;
	}

	@Override
	public String toString() {
		return "RegexUpdateModel [is_over=" + is_over + ", is_pre=" + is_pre + ", content=" + content + ", contentexp="
				+ contentexp + "]";
	}




}
