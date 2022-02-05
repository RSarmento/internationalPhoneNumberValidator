import React from "react";
import {Phone} from "../model/phone";

type Props = {
    item: Phone
}
export default ({item}: Props) => {

    return (
        <div>
            <span>{item.countryCode}</span>
            <span>{item.countryName}</span>
            <span>{item.number}</span>
            <span>{item.state ?"true":"false"} </span>
        </div>
    )
}
