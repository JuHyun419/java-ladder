package nextstep.step2.domain;

import nextstep.step2.generator.BooleanGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Line {

    static final String LADDER = "|";
    static final String LINE_LADDER = "-----";
    static final String BLANK = "     ";

    private List<Boolean> points = new ArrayList<>();

    public Line(int countOfParticipant, BooleanGenerator generator) {
        IntStream.range(0, countOfParticipant - 1)
                .forEach(i -> this.add(i, generator.generator()));
    }

    public Line(Boolean... booleans) {
        IntStream.range(0, booleans.length)
                .forEach(i -> this.add(i, booleans[i]));
    }

    private void add(int index, boolean generator) {
        if (existPoint(index)) {
            points.add(false);
            return;
        }

        points.add(generator);
    }

    private boolean existPoint(int index) {
        return index > 0 && points.get(index - 1);
    }

    public String drawLine() {
        return points.stream()
                .map(this::convertLine)
                .collect(Collectors.joining()) + LADDER;
    }

    private String convertLine(boolean point) {
        return LADDER + (point ? LINE_LADDER : BLANK);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;

        return Objects.equals(points, line.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(points);
    }
}
