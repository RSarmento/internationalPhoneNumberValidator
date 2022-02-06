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
        <div className="flex justify-between self-stretch gap-2">
            <span className="flex-1">{item.countryCode}</span>
            <span className="flex-1">{item.countryName}</span>
            <span className="flex-1">{item.number}</span>
            <span className="flex-1">{item.state ?"true":"false"} </span>
        </div>
    )
}
