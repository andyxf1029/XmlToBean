package com.ebao.gs.integration.mapping.reduce;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.jxpath.JXPathContext;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;
import org.xml.sax.SAXException;

import com.ebao.gs.integration.mapping.bean.Pair;
import com.ebao.gs.integration.mapping.configuration.IMappingRuleProvider;
import com.ebao.gs.integration.mapping.configuration.bean.Rule;
import com.ebao.gs.integration.mapping.configuration.bean.RuleSet;
import com.ebao.gs.integration.mapping.helper.IBeanDataSetter;
import com.ebao.gs.integration.mapping.helper.IDataFetcher;
import com.ebao.gs.integration.mapping.helper.ISpecialRuleProvider;
import com.ebao.gs.integration.mapping.helper.IToolsProiver;

public class DefaultReducer implements Reducer {

	private IDataFetcher dataFetcher;

	private IBeanDataSetter beanSetter;

	private ISpecialRuleProvider specialRuleProvider;

	private IMappingRuleProvider rulePorvider;

	private IToolsProiver toolsProvider;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.ebao.gs.integration.mapping.configuration.reduce.Reducer#reduce(java
	 * .util.List, com.ebao.gs.integration.mapping.configuration.mapper.Pair)
	 */
	public Map<String, Object> reduce(Pair pair, Map<String, Object> contextMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		List<RuleSet> rules = this.rulePorvider.loadRuleSetByKey(pair.getKey());
		Map<String, Object> resultMap = new HashMap<String, Object>();
		for (RuleSet ruleSet : rules) {
			this.processListData(pair, resultMap, ruleSet, contextMap);
		}

		return resultMap;
	}

	private void processListData(Pair pair, Map<String, Object> resultMap,
			RuleSet ruleSet, Map<String, Object> contextMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		List<Object> targetBeanList = new ArrayList<Object>();
		for (Object source : (List<Object>) pair.getValues()) {
			targetBeanList.add(this.callRules(source, resultMap, ruleSet,
					contextMap));
		}

		resultMap.put(ruleSet.getId(), targetBeanList);
	}

	private Object getSourceValue(Object value, Rule rule)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (!this.haveSpecialRule(rule)) {
			return this.dataFetcher.getValue(value, rule.getAcordPath());
		} else {
			return this.specialRuleProvider.call(rule.getSpecialRule(), value);
		}

	}

	private Object callRules(Object value, Map<String, Object> map,
			RuleSet ruleSet, Map<String, Object> contextMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException, IOException, SAXException {
		List<Rule> rules = ruleSet.getRules();
		Object targetBean = this.createBean(ruleSet, contextMap);
		Object context = beanSetter.createBeanContext(targetBean);
		for (Rule rule : rules) {
			if (haveRefRule(rule)) {
				List<RuleSet> ruleSetRefList = this.rulePorvider
						.loadRuleSetByKey(rule.getRef());
				Pair refPair = this.getRefPair(rule, value);
				for (RuleSet ruleSetRef : ruleSetRefList) {
					this.processListData(refPair, map, ruleSetRef, contextMap);
					this.doAfter(map, targetBean, rule, ruleSetRef);
				}

			} else {
				Object soureValue = this.getSourceValue(value, rule);
				if (StringUtils.isNotBlank(rule.getTool())) {
					soureValue = this.callTools(rule.getTool(), soureValue);
				}

				beanSetter.setValue(context, rule.getBeanPath(), soureValue);
			}

		}

		return targetBean;
	}

	private void doAfter(Map<String, Object> map, Object targetBean, Rule rule,
			RuleSet ruleSetRef) {
		if (StringUtils.isNotBlank(rule.getAfter())) {
			Object result = map.get(ruleSetRef.getId());
			Method method = ReflectionUtils.findMethod(targetBean.getClass(),
					rule.getAfter(), new Class[] { this.getArgType(result) });
			ReflectionUtils.invokeMethod(method, targetBean,
					new Object[] { result });
		}
	}

	private Class getArgType(Object result) {
		if (result instanceof List) {
			return List.class;
		}
		return result.getClass();
	}

	private Object callTools(String tool, Object soureValue)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		return toolsProvider.call(tool, soureValue);
	}

	private Pair getRefPair(Rule rule, Object value) {
		JXPathContext context = JXPathContext.newContext(value);
		return new Pair(rule.getRef(), context.selectNodes(rule.getPath()));
	}

	private boolean haveRefRule(Rule rule) {
		return rule.getPath() != null && rule.getRef() != null;
	}

	private boolean haveSpecialRule(Rule rule) {
		return StringUtils.isNotEmpty(rule.getSpecialRule());
	}

	private Object createBean(RuleSet ruleSet, Map<String, Object> contextMap)
			throws InstantiationException, IllegalAccessException,
			ClassNotFoundException {
		if (!this.contextFlagIsTrue(ruleSet.getContext())) {
			return Class.forName(ruleSet.getCreateBeanName()).newInstance();
		} else {
			if (contextMap.containsKey(ruleSet.getCreateBeanName())) {
				return contextMap.get(ruleSet.getCreateBeanName());
			} else {
				Object createBean = Class.forName(ruleSet.getCreateBeanName())
						.newInstance();
				contextMap.put(ruleSet.getCreateBeanName(), createBean);
				return createBean;
			}

		}
	}

	private boolean contextFlagIsTrue(String context) {
		return StringUtils.isNotBlank(context) && "true".equals(context);
	}

	public void setDataFetcher(IDataFetcher dataFetcher) {
		this.dataFetcher = dataFetcher;
	}

	public void setBeanSetter(IBeanDataSetter beanSetter) {
		this.beanSetter = beanSetter;
	}

	public void setSpecialRuleProvider(ISpecialRuleProvider specialRuleProvider) {
		this.specialRuleProvider = specialRuleProvider;
	}

	public void setRulePorvider(IMappingRuleProvider rulePorvider) {
		this.rulePorvider = rulePorvider;
	}

	public void setToolsProvider(IToolsProiver toolsProvider) {
		this.toolsProvider = toolsProvider;
	}

}
