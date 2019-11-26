package liquibase.ext.mongodb.statement;

/*-
 * #%L
 * Liquibase MongoDB Extension
 * %%
 * Copyright (C) 2019 Mastercard
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import com.mongodb.client.MongoDatabase;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = true)
public class CountDocumentsInCollection extends AbstractMongoStatement {

    public static final String COMMAND = "countDocumentsInCollection";

    private final String collectionName;

    @Override
    public String toJs() {
        return
                new StringBuilder()
                        .append("db.")
                        .append(COMMAND)
                        .append("(")
                        .append(collectionName)
                        .append(");")
                        .toString();
    }

    @Override
    public long queryForLong(MongoDatabase db) {
        return db.getCollection(collectionName).countDocuments();
    }

    @Override
    public String toString() {
        return this.toJs();
    }
}
