package com.aiexpanse.react.view.dictionary.impl;

import com.aiexpanse.react.view.dictionary.api.GuiDomain;
import com.aiexpanse.react.view.dictionary.api.GuiItem;
import com.aiexpanse.react.view.dictionary.api.GuiPath;
import com.aiexpanse.react.view.dictionary.api.GuiRelationship;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class DefaultGuiPath<F, T> implements GuiPath<F, T> {

    protected GuiItem<?, T> terminatingItem;
    protected final LinkedList<GuiRelationship<?, ?>> relationships = new LinkedList<>();
    private final AtomicReference<String> PATH = new AtomicReference<>(null);
    private final AtomicInteger HASH_CODE = new AtomicInteger(0);

    public DefaultGuiPath() {
        terminatingItem = null;
    }

    public DefaultGuiPath(GuiRelationship<?, ?> guiRelationship) {
        if (guiRelationship == null) {
            throw new IllegalArgumentException("Input guiRelationship must not be null");
        }
        relationships.add(guiRelationship);
        terminatingItem = null;
    }

    public DefaultGuiPath(GuiItem<?, T> guiItem) {
        if (guiItem == null) {
            throw new IllegalArgumentException("Input guiItem must not be null");
        }
        terminatingItem = guiItem;
    }

    public DefaultGuiPath(List<GuiRelationship<?, ?>> guiRelationships, GuiItem<?, T> guiItem) {
        if (guiRelationships == null || guiRelationships.isEmpty()) {
            throw new IllegalArgumentException("Input guiRelationships must not be null");
        }
        relationships.addAll(guiRelationships);
        terminatingItem = guiItem;
    }

    public DefaultGuiPath(List<GuiRelationship<?, ?>> guiRelationships) {
        this(guiRelationships, null);
    }

    public DefaultGuiPath(GuiDomain<F> domain) {
        relationships.add(DefaultGuiRelationship.newInstance(domain, domain, domain.getName()));
        terminatingItem = null;
    }

    @Override
    public GuiDomain<F> getStartingDomain() {
        GuiDomain<F> guiDomain;
        if (relationships.isEmpty()) {
            guiDomain = (GuiDomain<F>) terminatingItem.getDomain();
        } else {
            guiDomain = (GuiDomain<F>) relationships.getFirst().getDomain();
        }
        return guiDomain;
    }

    @Override
    public boolean isTerminatedWithAnItem() {
        return terminatingItem != null;
    }

    private GuiDomain<?> getEndingDomain(GuiPath<?, ?> guiPath) {
        GuiRelationship<?, ?> endingRelationship = guiPath.getEndingRelationship();
        return endingRelationship != null ? endingRelationship.getEndingDomain() : null;
    }

    @Override
    public GuiRelationship<?, ?> getEndingRelationship() {
        return relationships.peekLast();
    }

    @Override
    public GuiItem<?, T> getTerminatingItem() {
        return terminatingItem;
    }

    @Override
    public List<GuiRelationship<?, ?>> getRelationships() {
        return Collections.unmodifiableList(relationships);
    }

    @Override
    public <E> GuiPath<F, E> append(GuiPath<? extends T, E> path) {
        assertNonTerminating(path);
        assertSameStartingDomain(path);
        GuiPath<F, E> newPath;
        if (equals(path)) {
            if (path.isTerminatedWithAnItem()) {
                newPath = new DefaultGuiPath<>(path.getRelationships(), path.getTerminatingItem());
            } else {
                newPath = new DefaultGuiPath<>(path.getRelationships());
            }
        } else {
            List<GuiRelationship<?, ?>> guiRelationships = new LinkedList<>();
            guiRelationships.addAll(relationships);
            if (path.isTerminatedWithAnItem()) {
                newPath = new DefaultGuiPath<>(guiRelationships, path.getTerminatingItem());
            } else {
                if (!getEndingRelationship().getEndingDomain().equals(path.getEndingRelationship().getEndingDomain())) {
                    guiRelationships.addAll(path.getRelationships());
                }
                newPath = new DefaultGuiPath<>(guiRelationships);
            }
        }
        return newPath;
    }

    @Override
    public <S> GuiPath<S, T> prepend(GuiPath<S, F> path) {
        assertNonTerminating(path);
        assertSameStartingDomain(path);
        GuiPath<S, T> newPath;
        if (equals(path)) {
            if (path.isTerminatedWithAnItem()) {
                newPath = new DefaultGuiPath<>(relationships, terminatingItem);
            } else {
                newPath = new DefaultGuiPath<>(relationships);
            }
        } else {
            List<GuiRelationship<?, ?>> guiRelationships = new LinkedList<>();
            guiRelationships.addAll(path.getRelationships());
            guiRelationships.addAll(relationships);
            if (path.isTerminatedWithAnItem()) {
                newPath = new DefaultGuiPath<>(guiRelationships, terminatingItem);
            } else {
                newPath = new DefaultGuiPath<>(guiRelationships);
            }
        }
        return newPath;
    }

    @Override
    public <S> GuiPath<S, F> getParent() {
        GuiPath<S, F> parentGuiPath;
        if (isTerminatedWithAnItem()) {
            if (relationships.isEmpty()) {
                parentGuiPath = new DefaultGuiPath<>((GuiDomain)terminatingItem.getDomain());
            } else {
                parentGuiPath = new DefaultGuiPath<>(relationships);
            }
        } else {
            int size = relationships.size();
            if (size == 1) {
                parentGuiPath = null;
            } else {
                parentGuiPath = new DefaultGuiPath<>(relationships.subList(0, size-1));
            }
        }
        return parentGuiPath;
    }

    @Override
    public String toPathString() {
        return toString();
    }

    @Override
    public void addRelationship(GuiRelationship<?, ?> guiRelationship) {
        relationships.add(guiRelationship);
    }

    @Override
    public void addRelationships(List<GuiRelationship<?, ?>> guiRelationships) {
        relationships.addAll(guiRelationships);
    }

    @Override
    public int compareTo(GuiPath<?, ?> o) {
        return toString().compareTo(o.toString());
    }

    @Override
    public String toString() {
        String path = PATH.get();
        if (path == null) {
            GuiDomain startingDomain = getStartingDomain();
            StringBuilder pathBuilder = new StringBuilder().append(startingDomain.getName());
            if (!relationships.isEmpty()) {
                Iterator<GuiRelationship<?, ?>> iterator = relationships.iterator();
                GuiRelationship<?, ?> relationship = iterator.next();
                if (!startingDomain.equals(relationship.getDomain()) ||
                        !relationship.getDomain().equals(relationship.getEndingDomain())) {
                    pathBuilder.append(PATH_SEP).append(relationship.getName());
                }
                while (iterator.hasNext()) {
                    relationship = iterator.next();
                    pathBuilder.append(PATH_SEP).append(relationship.getName());
                }
            }
            if (isTerminatedWithAnItem()) {
                pathBuilder.append(PATH_SEP).append(terminatingItem.getName());
            }
            PATH.compareAndSet(null, pathBuilder.toString());
            path = PATH.get();
        }
        return path;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (!(other instanceof GuiPath)) {
            return false;
        }
        GuiPath<?, ?> that = (GuiPath)other;
        return toString().equals(that.toString());
    }

    @Override
    public int hashCode() {
        int result = HASH_CODE.get();
        if (result == 0) {
            result = toString().hashCode();
            HASH_CODE.compareAndSet(0, result);
        }
        return result;
    }

    private void assertNonTerminating(GuiPath path) {
        if (path.isTerminatedWithAnItem()) {
            throw new IllegalArgumentException("path: " + path + " is terminated with an item");
        }
    }

    private void assertSameStartingDomain(GuiPath path) {
        if (!path.getStartingDomain().equals(getEndingDomain(this))) {
            throw new IllegalArgumentException("Starting domain with path: " + path +
                    " is different from ending domain with path: " + this);
        }
    }

}
