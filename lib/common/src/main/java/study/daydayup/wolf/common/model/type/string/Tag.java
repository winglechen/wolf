package study.daydayup.wolf.common.model.type.string;

import study.daydayup.wolf.common.model.contract.DataType;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

/**
 * study.daydayup.wolf.common.model.type.string
 *
 * @author Wingle
 * @since 2019/10/15 4:57 下午
 **/
public class Tag implements DataType {
    private String delimiter = ",";
    private Set<String> tags;

    public Tag() {
        this(null, null);
    }

    public Tag(String tagString) {
        this(tagString, null);
    }

    public Tag(String tagString, String delimiter) {
        tags = new TreeSet<>();

        if (delimiter != null) {
            this.delimiter = delimiter;
        }
        parseStringTag(tagString);
    }

    public Tag add(String tag) {
        if (tag == null) {
            return this;
        }

        tag = tag.trim();
        if (tags.contains(tag)) {
            return this;
        }

        tags.add(tag);
        return this;
    }

    public Tag addString(String tagString) {
        parseStringTag(tagString);
        return this;
    }

    public Tag addAll(Collection<String> tagList) {
        for (String tag: tagList ) {
            add(tag);
        }
        return this;
    }

    public boolean contains(String tag) {
        if (tag == null) {
            return false;
        }

        return tags.contains(tag.trim());
    }

    public String toString() {
        return String.join(delimiter, tags);
    }

    private void parseStringTag(String tagString) {
        if (tagString == null) {
            return;
        }

        String[] tagArray = tagString.split(delimiter);
        if (0 == tagArray.length) {
            return;
        }

        for (String tag : tagArray ) {
            add(tag);
        }
    }

}
