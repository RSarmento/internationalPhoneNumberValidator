import Item from "./Item";
import {Phone} from "../model/phone";

type Props = {
    items: Phone[]
}
export default ({items}: Props) => {
    return (
        <div className="self-stretch">
            {items.map(item => {
                return (
                    <Item item={item}/>
                )
            })}
        </div>
    )
}
