import axios from "axios";
import {Phone} from "../model/phone";

export interface Pageable{
    content: Phone[],
    last: boolean,
    number: number
}
export interface GetParams{
    page: number
}
export interface GetParamsByCountryAndState {
    country: string,
    page: number,
    state: boolean
}
export class ItemService{
    getCategorize(params: GetParams){
        return axios
            .get<Pageable>("http://localhost:8080/api/v1/categorize", {params})
            .then(res => res.data)
    }
    getCategorizeByCountryAndState(params: GetParamsByCountryAndState) {
        return axios
            .get<Pageable>("http://localhost:8080/api/v1/categorize/byCountryAndState", {params} )
            .then(res => res.data)
    }
}
