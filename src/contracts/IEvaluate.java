package contracts;

import models.Cell;
import models.Grid;
import models.Node;

public interface IEvaluate {
    Double evaluate(Node node, Grid grid, Cell goal);
}
