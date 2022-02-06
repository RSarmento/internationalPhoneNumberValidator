import React from "react";
import {Phone} from "../model/phone";

type Props = {
    item: Phone
}
const item = ({item}: Props) => {
    return (
        <div className="flex justify-between self-stretch gap-2">
            <span className="flex-1">{item.countryCode}</span>
            <span className="flex-1">{item.countryName}</span>
            <span className="flex-1">{item.number}</span>
            <span className="flex-1">{item.state ?"Valid":"Invalid"} </span>
        </div>
    )
}
export default item
