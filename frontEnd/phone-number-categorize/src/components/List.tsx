import Item from "./Item";
import {Phone} from "../model/phone";

type Props = {
    items: Phone[]
}
export default ({items}: Props) => {
    return (
        <div>
            {items.map(item => {
                return (
                    <Item item={item}/>
                )
            })}
        </div>
    )
}
