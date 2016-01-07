/*
 * Copyright (c) 2013-2016 GraphAware
 *
 * This file is part of the GraphAware Framework.
 *
 * GraphAware Framework is free software: you can redistribute it and/or modify it under the terms of
 * the GNU General Public License as published by the Free Software Foundation, either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details. You should have received a copy of
 * the GNU General Public License along with this program.  If not, see
 * <http://www.gnu.org/licenses/>.
 */

package com.graphaware.module.algo.path;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.graphaware.api.JsonNode;
import com.graphaware.api.JsonRelationship;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Path;
import org.neo4j.graphdb.Relationship;

import java.util.LinkedList;
import java.util.List;

/**
 * JSON-serializable representation of a Neo4j path.
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class JsonPath {

    private JsonNode[] nodes;
    private JsonRelationship[] relationships;
    private Long cost;

    public JsonPath(Path path, JsonPathFinderInput jsonInput) {
        List<JsonNode> jsonNodes = new LinkedList<>();
        for (Node node : path.nodes()) {
            jsonNodes.add(new JsonNode(node, jsonInput));
        }

        List<JsonRelationship> jsonRelationships = new LinkedList<>();
        for (Relationship relationship : path.relationships()) {
            jsonRelationships.add(new JsonRelationship(relationship, jsonInput));
        }

        setNodes(jsonNodes.toArray(new JsonNode[jsonNodes.size()]));
        setRelationships(jsonRelationships.toArray(new JsonRelationship[jsonRelationships.size()]));

        if (path instanceof WeightedPath) {
            setCost(((WeightedPath) path).getCost());
        }
    }

    public JsonNode[] getNodes() {
        return nodes;
    }

    public void setNodes(JsonNode[] nodes) {
        this.nodes = nodes;
    }

    public JsonRelationship[] getRelationships() {
        return relationships;
    }

    public void setRelationships(JsonRelationship[] relationships) {
        this.relationships = relationships;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
