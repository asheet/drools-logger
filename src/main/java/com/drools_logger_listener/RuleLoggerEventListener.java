package com.drools_logger_listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.kie.api.definition.rule.Rule;
import org.kie.api.event.rule.AfterMatchFiredEvent;
import org.kie.api.event.rule.AgendaEventListener;
import org.kie.api.event.rule.BeforeMatchFiredEvent;
import org.kie.api.event.rule.MatchCancelledEvent;
import org.kie.api.event.rule.MatchCreatedEvent;
import org.kie.api.runtime.rule.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RuleLoggerEventListener implements AgendaEventListener{
		protected static final Logger logger = LoggerFactory.getLogger(RuleLoggerEventListener.class);
	    private List<Match> matchList = new ArrayList<Match>();

	    public RuleLoggerEventListener() {
	        // intentionally left blank
	    }

	    
	    public void matchCreated(MatchCreatedEvent event) {
	        logger.info( "MatchCreated: " + event.toString() );
	    }
	    
	    public void matchCancelled(MatchCancelledEvent event) {
	        logger.info( "MatchCancelled: " + event.toString() );
	    }
	    
	    public void beforeMatchFired(BeforeMatchFiredEvent event) {
	        logger.info( "beforeMatchFired: " +  event.toString() );
	    }
	    
	    /* Used from:
	     * https://stackoverflow.com/questions/19951880/drools-how-to-find-out-which-all-rules-were-matched
	     */
	    public void afterMatchFired(AfterMatchFiredEvent event) {
	    	 Rule rule = event.getMatch().getRule();

	         String ruleName = rule.getName();
	         Map<String, Object> ruleMetaDataMap = rule.getMetaData();

	         matchList.add(event.getMatch());
	         StringBuilder sb = new StringBuilder("Rule fired: " + ruleName);

	         if (ruleMetaDataMap.size() > 0) {
	             sb.append("\n  With [" + ruleMetaDataMap.size() + "] meta-data:");
	             for (String key : ruleMetaDataMap.keySet()) {
	                 sb.append("\n    key=" + key + ", value="
	                         + ruleMetaDataMap.get(key));
	             }
	         }

	         logger.info( sb.toString() );
	     }

	     public boolean isRuleFired(String ruleName) {
	         for (Match a : matchList) {
	             if (a.getRule().getName().equals(ruleName)) {
	                 return true;
	             }
	         }
	         return false;
	     }

	     public void reset() {
	         matchList.clear();
	     }

	     public final List<Match> getMatchList() {
	         return matchList;
	     }

	     public String matchsToString() {
	         if (matchList.size() == 0) {
	             return "No matchs occurred.";
	         } else {
	             StringBuilder sb = new StringBuilder("Matchs: ");
	             for (Match match : matchList) {
	                 sb.append("\n  rule: ").append(match.getRule().getName());
	             }
	             return sb.toString();
	         }
	     }

	    
	    public void agendaGroupPopped(org.kie.api.event.rule.AgendaGroupPoppedEvent event) {
	        logger.info( "agendaGroupPopped: " + event.toString() );
	    }
	    
	    public void agendaGroupPushed(org.kie.api.event.rule.AgendaGroupPushedEvent event) {
	        logger.info( "agendaGroupPushed: " + event.toString() );
	    }
	    
	    public void beforeRuleFlowGroupActivated(org.kie.api.event.rule.RuleFlowGroupActivatedEvent event) {
	        logger.info( "beforeRuleFlowGroupActivated: " + event.toString() );
	    }
	    
	    public void afterRuleFlowGroupActivated(org.kie.api.event.rule.RuleFlowGroupActivatedEvent event) {
	        logger.info( "afterRuleFlowGroupActivated: " + event.toString() );
	    }
	    
	    public void beforeRuleFlowGroupDeactivated(org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent event) {
	        logger.info( "beforeRuleFlowGroupDeactivated: " + event.toString() );
	    }
	    
	    public void afterRuleFlowGroupDeactivated(org.kie.api.event.rule.RuleFlowGroupDeactivatedEvent event) {
	        logger.info( "afterRuleFlowGroupDeactivated: " + event.toString() );
	    }
}
