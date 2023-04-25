package com.github.verhagen.atnb.domain;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.Collection;

public class IdentifierCatalogImpl implements IdentifierCatalog {
    private Logger logger = LoggerFactory.getLogger(IdentifierCatalog.class);
    private static Map<String, String> aliasSearchKeys = new HashMap<>();
    private Map<String, String> identifiers = new HashMap<>();
    private Node<String, String> identifierTree = new Node<>("__root__", null, null);

    static {
        aliasSearchKeys.put("b", "book");
        aliasSearchKeys.put("p", "project");
        aliasSearchKeys.put("l", "language");
    }

    public void add(List<String> identifiers) {
        IdentifierValidationImpl validation = new IdentifierValidationImpl();
        for (String identifier : identifiers) {
            if (validation.isValid(identifier)) {
                this.identifiers.put(identifier, identifier);
                addNode(identifier);
            }
        }
    }

    private void addNode(String identifier) {
        identifierTree.add(Arrays.asList(identifier.split("\\.")), identifier);
    }

    public Collection<String> getIdentifiers() {
        return identifiers.values();
    }

    public void accept(Visitor<Node<String, String>> visitor) {
        identifierTree.accept(visitor);
    }

    public List<String> search(String query) {
        String queryWithoutAliases = resolveAliases(query);
        if (! new IdentifierValidationImpl().isValid(queryWithoutAliases)) {
            throw IdentifierValidationImpl.createIllegalArgumentException("query", query);
        }

        IdentifierCatalogImpl.MatchFinder matchFinder = new IdentifierCatalogImpl.MatchFinder(queryWithoutAliases);
        accept(matchFinder);
        return matchFinder.getMatches();
    }

    private String resolveAliases(String query) {
        if (! query.contains(":")) {
            return query;
        }

        String queryAlias = query.substring(0, query.indexOf(":"));
        String queryRemainer = query.substring(query.indexOf(":") +1);
        if (aliasSearchKeys.containsKey(queryAlias)) {
            return aliasSearchKeys.get(queryAlias) + "." + queryRemainer;
        }
        logger.info("Argument 'query' with value '" + query +"' contains alias '" + queryAlias
                + "', which is not a known alias. Known aliases are: " + aliasSearchKeys);
        return "";
    }

    class MatchFinder implements Visitor<Node<String, String>> {
        List<String> matches = new ArrayList<>();
        String query;

        public MatchFinder(String query) {
            this.query = query;
        }

        @Override
        public void visit(Node<String, String> node) {
            if (node.getValue() == null) {
                return;
            }

            if (node.getValue().contains(query)) {
                matches.add(node.getValue());
            }
        }
        public List<String> getMatches() {
            return matches;
        }
    }

    public boolean isKnownIdentifier(String identifier) {
        return identifiers.containsValue(identifier);
    };

}
