import {UI_PATH_SEP} from "../constants/Def";
import {isString} from "./TypeUtils";

export const getRootName = (path) => (
    path.startsWith(UI_PATH_SEP) ? path.split(UI_PATH_SEP)[1] : path.split(UI_PATH_SEP)[0]
);

export const likeViewUIPath = (uipath) => (
    isString(uipath) && uipath.startsWith(UI_PATH_SEP) && uipath.split(UI_PATH_SEP).length > 3
);