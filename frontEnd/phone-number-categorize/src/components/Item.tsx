import React from "react";
import {Phone} from "../model/phone";
import {ItemService} from "../services/item.service"

type Props = {
    item: Phone
    // ,
    // itemService: ItemService
}


export default ({item}: Props
                // , {itemService}: Props
) => {

    const pageable = ""
    // itemService.getAll(pageable);
    return (
        <div>
            <span>{item.countryCode}</span>
            <span>{item.countryName}</span>
            <span>{item.number}</span>
            <span>{item.state ?"true":"false"} </span>
        </div>
    )
}
